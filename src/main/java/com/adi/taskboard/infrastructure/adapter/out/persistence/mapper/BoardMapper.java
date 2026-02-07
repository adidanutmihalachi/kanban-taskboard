package com.adi.taskboard.infrastructure.adapter.out.persistence.mapper;

import com.adi.taskboard.domain.model.Board;
import com.adi.taskboard.infrastructure.adapter.out.persistence.entity.BoardJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class BoardMapper {

    public BoardJpaEntity toEntity(Board board) {
        return BoardJpaEntity.builder()
                .id(board.getId())
                .title(board.getTitle())
                .description(board.getDescription())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }

    public Board toDomain(BoardJpaEntity entity) {
        return Board.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
