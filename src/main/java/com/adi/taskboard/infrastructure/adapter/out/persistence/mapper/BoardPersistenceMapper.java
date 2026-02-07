package com.adi.taskboard.infrastructure.adapter.out.persistence.mapper;

import com.adi.taskboard.domain.model.Board;
import com.adi.taskboard.infrastructure.adapter.out.persistence.entity.BoardJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class BoardPersistenceMapper {

    private final ColumnPersistenceMapper columnPersistenceMapper;

    public BoardJpaEntity toEntity(Board board) {
        if (board == null) return null;

        return BoardJpaEntity.builder()
                .id(board.getId())
                .title(board.getTitle())
                .description(board.getDescription())
                .createdAt(board.getCreatedAt())
                .build();
    }

    public Board toDomain(BoardJpaEntity entity) {
        if (entity == null) return null;

        return Board.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .columns(entity.getColumns() != null ?
                        entity.getColumns().stream()
                                .map(columnPersistenceMapper::toDomain)
                                .collect(Collectors.toList()) :
                        null)
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
