package com.adi.taskboard.domain.port.out;

import com.adi.taskboard.domain.model.Board;

import java.util.Optional;

public interface BoardRepositoryPort {
    Board save(Board board);
    Optional<Board> findById(Long id);
    void deleteById(Long id);
}
