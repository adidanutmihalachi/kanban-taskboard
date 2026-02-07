package com.adi.taskboard.domain.port.out;

import com.adi.taskboard.domain.model.Column;

import java.util.Optional;

public interface ColumnRepositoryPort {
    Column save(Column column);
    Optional<Column> findById(Long id);
    void deleteById(Long id);
}
