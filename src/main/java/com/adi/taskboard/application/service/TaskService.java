package com.adi.taskboard.application.service;

import com.adi.taskboard.domain.model.Priority;
import com.adi.taskboard.domain.model.Task;
import com.adi.taskboard.domain.port.in.CreateTaskUseCase;
import com.adi.taskboard.domain.port.in.MoveTaskUseCase;
import com.adi.taskboard.domain.port.out.ColumnRepositoryPort;
import com.adi.taskboard.domain.port.out.TaskRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
@Transactional
public class TaskService implements MoveTaskUseCase, CreateTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;
    private final ColumnRepositoryPort columnRepositoryPort;

    @Override
    public Task create(String title, String description, Long columnId, Priority priority) {
        var column = columnRepositoryPort.findById(columnId)
                .orElseThrow(() -> new IllegalArgumentException("Column Not Found"));

        Integer position = column.getTasks() != null ? column.getTasks().size() : 0;

        Task task = Task.builder()
                .title(title)
                .description(description)
                .position(position)
                .priority(priority != null ? priority : Priority.MEDIUM)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return taskRepositoryPort.save(task);
    }

    @Override
    public Task moveTask(Long taskId, Long targetColumnId, Integer newPosition) {
        var task = taskRepositoryPort.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task Not Found"));

        var targetColumn = columnRepositoryPort.findById(targetColumnId)
                .orElseThrow(() -> new RuntimeException("Target Column Not Found"));

        task.updatePosition(newPosition);

        return taskRepositoryPort.save(task);
    }
}
