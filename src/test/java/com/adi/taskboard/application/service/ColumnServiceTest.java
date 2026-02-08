package com.adi.taskboard.application.service;

import com.adi.taskboard.domain.model.Board;
import com.adi.taskboard.domain.model.Column;
import com.adi.taskboard.domain.port.out.BoardRepositoryPort;
import com.adi.taskboard.domain.port.out.ColumnRepositoryPort;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ColumnServiceTest {

    @Mock
    private ColumnRepositoryPort columnRepository;

    @Mock
    private BoardRepositoryPort boardRepository;

    @InjectMocks
    private ColumnService columnService;

    @Test
    void createShouldUseBoardColumnsWhenPositionOmitted() {
        ArrayList<Column> columns = new ArrayList<>();
        columns.add(Column.builder().id(1L).build());
        Board board = Board.builder().id(1L).columns(columns).build();
        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));
        when(columnRepository.save(any(Column.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());

        Column created = columnService.create("Backlog", 1L, null);

        assertEquals(columns.size(), created.getPosition());
        verify(columnRepository).save(argThat(column -> column.getPosition().equals(columns.size())));
    }

    @Test
    void createShouldRespectProvidedPosition() {
        Board board = Board.builder().id(1L).columns(new ArrayList<>()).build();
        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));
        when(columnRepository.save(any(Column.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());

        Column result = columnService.create("Done", 1L, 5);

        assertEquals(5, result.getPosition());
        verify(columnRepository).save(argThat(column -> column.getPosition().equals(5)));
    }

    @Test
    void createShouldThrowWhenBoardMissing() {
        when(boardRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException error = assertThrows(RuntimeException.class,
                () -> columnService.create("Blocked", 1L, 2));

        assertEquals("Board not found", error.getMessage());
        verifyNoInteractions(columnRepository);
    }
}

