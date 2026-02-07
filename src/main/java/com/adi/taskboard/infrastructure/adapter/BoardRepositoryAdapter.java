package com.adi.taskboard.infrastructure.adapter;

import com.adi.taskboard.domain.model.Board;
import com.adi.taskboard.domain.port.out.BoardRepositoryPort;
import com.adi.taskboard.infrastructure.adapter.out.persistence.mapper.BoardPersistenceMapper;
import com.adi.taskboard.infrastructure.adapter.out.persistence.repository.BoardJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BoardRepositoryAdapter implements BoardRepositoryPort {

    private final BoardJpaRepository boardJpaRepository;
    private final BoardPersistenceMapper boardPersistenceMapper;


    @Override
    public Board save(Board board) {
        var entity = boardPersistenceMapper.toEntity(board);
        var savedEntity = boardJpaRepository.save(entity);
        return boardPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Board> findById(Long id) {
        return boardJpaRepository.findById(id)
                .map(boardPersistenceMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        boardJpaRepository.deleteById(id);
    }
}
