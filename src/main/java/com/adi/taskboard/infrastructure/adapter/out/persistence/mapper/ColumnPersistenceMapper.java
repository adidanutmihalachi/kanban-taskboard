package com.adi.taskboard.infrastructure.adapter.out.persistence.mapper;

import com.adi.taskboard.domain.model.Column;
import com.adi.taskboard.infrastructure.adapter.out.persistence.entity.ColumnJpaEntity;
import com.adi.taskboard.infrastructure.adapter.out.persistence.repository.TaskJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ColumnPersistenceMapper {

    private final TaskPersistenceMapper taskMapper;

    public ColumnJpaEntity toEntity(Column column) {
        if (column == null) return null;

        return ColumnJpaEntity.builder()
                .id(column.getId())
                .title(column.getTitle())
                .position(column.getPosition())
                .build();
    }

    public Column toDomain(ColumnJpaEntity entity) {
        if (entity == null) return null;

        return Column.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .position(entity.getPosition())
                .tasks(entity.getTasks() != null ?
                        entity.getTasks().stream()
                                .map(taskMapper::toDomain)
                                .collect(Collectors.toList()): null)
                .build();
    }
}
