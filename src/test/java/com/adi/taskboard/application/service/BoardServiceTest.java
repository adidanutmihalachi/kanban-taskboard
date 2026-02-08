package com.adi.taskboard.application.service;

import com.adi.taskboard.domain.model.Board;
import com.adi.taskboard.domain.port.out.BoardRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoardServiceTest {

    @Mock
    private BoardRepositoryPort boardRepositoryPort;

    @InjectMocks
    private BoardService boardService;

    @Test
    void createShouldPersistNewBoardWithTimestamp() {
        when(boardRepositoryPort.save(any(Board.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());

        Board created = boardService.create("Tablero", "Descripción");

        assertEquals("Tablero", created.getTitle());
        assertEquals("Descripción", created.getDescription());
        assertNotNull(created.getCreatedAt());
        verify(boardRepositoryPort, times(1)).save(any(Board.class));
    }

    @Test
    void getByIdShouldReturnExistingBoard() {
        Board board = Board.builder().id(1L).title("Board").build();
        when(boardRepositoryPort.findById(1L)).thenReturn(Optional.of(board));

        Board result = boardService.getById(1L);

        assertSame(board, result);
    }

    @Test
    void getByIdShouldThrowWhenMissing() {
        when(boardRepositoryPort.findById(1L)).thenReturn(Optional.empty());

        RuntimeException error = assertThrows(RuntimeException.class, () -> boardService.getById(1L));

        assertEquals("Board not found", error.getMessage());
    }
}

