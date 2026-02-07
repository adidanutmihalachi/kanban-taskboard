package com.adi.taskboard.infrastructure.adapter.out.persistence.repository;

import com.adi.taskboard.infrastructure.adapter.out.persistence.entity.BoardJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<BoardJpaEntity, Long> {
}
