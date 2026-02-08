package com.adi.taskboard.application.service;

import com.adi.taskboard.domain.model.Column;
import com.adi.taskboard.domain.model.Priority;
import com.adi.taskboard.domain.model.Task;
import com.adi.taskboard.domain.port.out.ColumnRepositoryPort;
import com.adi.taskboard.domain.port.out.TaskRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepositoryPort taskRepositoryPort;

    @Mock
    private ColumnRepositoryPort columnRepositoryPort;

    @InjectMocks
    private TaskService taskService;

    @Test
    void createShouldUseExplicitPriority() {
        Column column = Column.builder().id(1L).tasks(new ArrayList<>()).build();
        when(columnRepositoryPort.findById(1L)).thenReturn(Optional.of(column));
        when(taskRepositoryPort.save(any(Task.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());

        Task task = taskService.create("Tarea", "Desc", 1L, Priority.HIGH);

        assertEquals(Priority.HIGH, task.getPriority());
        assertEquals(0, task.getPosition());
        verify(taskRepositoryPort).save(argThat(saved -> saved.getPriority() == Priority.HIGH));
    }

    @Test
    void createShouldDefaultPriorityToMedium() {
        Column column = Column.builder().id(1L).tasks(new ArrayList<>()).build();
        when(columnRepositoryPort.findById(1L)).thenReturn(Optional.of(column));
        when(taskRepositoryPort.save(any(Task.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());

        Task task = taskService.create("Tarea", "Desc", 1L, null);

        assertEquals(Priority.MEDIUM, task.getPriority());
    }

    @Test
    void createShouldThrowWhenColumnMissing() {
        when(columnRepositoryPort.findById(1L)).thenReturn(Optional.empty());

        IllegalArgumentException error = assertThrows(IllegalArgumentException.class,
                () -> taskService.create("T", "", 1L, Priority.LOW));

        assertEquals("Column Not Found", error.getMessage());
        verifyNoInteractions(taskRepositoryPort);
    }

    @Test
    void moveTaskUpdatesPosition() {
        Task task = Task.builder().id(1L).position(5).build();
        Column target = Column.builder().id(2L).build();
        when(taskRepositoryPort.findById(1L)).thenReturn(Optional.of(task));
        when(columnRepositoryPort.findById(2L)).thenReturn(Optional.of(target));
        when(taskRepositoryPort.save(any(Task.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());

        Task moved = taskService.moveTask(1L, 2L, 3);

        assertEquals(3, moved.getPosition());
        verify(taskRepositoryPort).save(task);
    }

    @Test
    void moveTaskShouldThrowWhenTaskMissing() {
        when(taskRepositoryPort.findById(1L)).thenReturn(Optional.empty());
        RuntimeException error = assertThrows(RuntimeException.class,
                () -> taskService.moveTask(1L, 2L, 3));
        assertEquals("Task Not Found", error.getMessage());
    }

    @Test
    void moveTaskShouldThrowWhenTargetColumnMissing() {
        Task task = Task.builder().id(1L).position(5).build();
        when(taskRepositoryPort.findById(1L)).thenReturn(Optional.of(task));
        when(columnRepositoryPort.findById(2L)).thenReturn(Optional.empty());

        RuntimeException error = assertThrows(RuntimeException.class,
                () -> taskService.moveTask(1L, 2L, 3));
        assertEquals("Target Column Not Found", error.getMessage());
    }
}

