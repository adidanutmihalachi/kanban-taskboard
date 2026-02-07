package com.adi.taskboard.infrastructure.adapter.out.persistence.repository;

import com.adi.taskboard.infrastructure.adapter.out.persistence.entity.ColumnJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnJpaRepository extends JpaRepository<ColumnJpaEntity, Long> {
}
