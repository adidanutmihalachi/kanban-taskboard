package com.adi.taskboard.infrastructure.adapter.out.persistence.mapper;

import com.adi.taskboard.domain.model.Priority;
import com.adi.taskboard.domain.model.Task;
import com.adi.taskboard.infrastructure.adapter.out.persistence.entity.PriorityJpaEntity;
import com.adi.taskboard.infrastructure.adapter.out.persistence.entity.TaskJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskPersistenceMapper {

    public TaskJpaEntity toEntity(Task task) {
        if (task == null) return null;

        return TaskJpaEntity.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .position(task.getPosition())
                .priority(toPriorityJpa(task.getPriority()))
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }

    public Task toDomain(TaskJpaEntity entity) {
        if (entity == null) return null;

        return Task.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .position(entity.getPosition())
                .priority(toPriorityDomain(entity.getPriority()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    private PriorityJpaEntity toPriorityJpa(Priority priority) {
        if (priority == null) return PriorityJpaEntity.MEDIUM;
        return PriorityJpaEntity.valueOf(priority.name());
    }

    private Priority toPriorityDomain(PriorityJpaEntity priorityJpaEntity) {
        if (priorityJpaEntity == null) return Priority.MEDIUM;
        return Priority.valueOf(priorityJpaEntity.name());
    }
}
