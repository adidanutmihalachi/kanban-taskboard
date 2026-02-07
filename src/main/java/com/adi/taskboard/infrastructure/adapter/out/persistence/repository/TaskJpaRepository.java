package com.adi.taskboard.infrastructure.adapter.out.persistence.repository;

import com.adi.taskboard.infrastructure.adapter.out.persistence.entity.TaskJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskJpaRepository extends JpaRepository<TaskJpaEntity, Long> {
}
