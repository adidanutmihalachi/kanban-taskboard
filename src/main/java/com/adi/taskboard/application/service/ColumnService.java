package com.adi.taskboard.application.service;

import com.adi.taskboard.domain.model.Column;
import com.adi.taskboard.domain.port.in.CreateColumnUseCase;
import com.adi.taskboard.domain.port.out.BoardRepositoryPort;
import com.adi.taskboard.domain.port.out.ColumnRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ColumnService implements CreateColumnUseCase {

    private final ColumnRepositoryPort columnRepository;
    private final BoardRepositoryPort boardRepository;

    @Override
    public Column create(String title, Long boardId, Integer position) {
        var board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));

        if (position == null) {
            position = board.getColumns() != null ? board.getColumns().size() : 0;
        }

        Column column = Column.builder()
                .title(title)
                .position(position)
                .build();

        return columnRepository.save(column);
    }
}
