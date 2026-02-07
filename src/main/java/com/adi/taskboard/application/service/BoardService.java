package com.adi.taskboard.application.service;

import com.adi.taskboard.domain.model.Board;
import com.adi.taskboard.domain.port.in.CreateBoardUseCase;
import com.adi.taskboard.domain.port.in.GetBoardUseCase;
import com.adi.taskboard.domain.port.out.BoardRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardService implements CreateBoardUseCase, GetBoardUseCase {

    private final BoardRepositoryPort boardRepositoryPort;

    @Override
    public Board create(String title, String description) {
        Board board = Board.builder()
                .title(title)
                .description(description)
                .createdAt(LocalDateTime.now())
                .build();
        return boardRepositoryPort.save(board);
    }

    @Override
    public Board getById(Long id) {
        return boardRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
    }
}
