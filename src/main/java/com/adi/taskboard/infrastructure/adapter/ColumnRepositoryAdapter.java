package com.adi.taskboard.infrastructure.adapter;

import com.adi.taskboard.domain.model.Column;
import com.adi.taskboard.domain.port.out.ColumnRepositoryPort;
import com.adi.taskboard.infrastructure.adapter.out.persistence.mapper.ColumnPersistenceMapper;
import com.adi.taskboard.infrastructure.adapter.out.persistence.repository.ColumnJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ColumnRepositoryAdapter implements ColumnRepositoryPort {

    private final ColumnJpaRepository jpaRepository;
    private final ColumnPersistenceMapper columnPersistenceMapper;

    @Override
    public Column save(Column column) {
        var entity = columnPersistenceMapper.toEntity(column);
        var savedEntity = jpaRepository.save(entity);
        return columnPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Column> findById(Long id) {
        return jpaRepository.findById(id)
                .map(columnPersistenceMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
