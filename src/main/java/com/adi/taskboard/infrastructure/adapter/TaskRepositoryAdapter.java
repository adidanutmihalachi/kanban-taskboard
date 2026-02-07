package com.adi.taskboard.infrastructure.adapter;

import com.adi.taskboard.domain.model.Task;
import com.adi.taskboard.domain.port.out.TaskRepositoryPort;
import com.adi.taskboard.infrastructure.adapter.out.persistence.mapper.TaskPersistenceMapper;
import com.adi.taskboard.infrastructure.adapter.out.persistence.repository.TaskJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskRepositoryAdapter implements TaskRepositoryPort {

    private final TaskJpaRepository taskJpaRepository;
    private final TaskPersistenceMapper taskMapper;

    @Override
    public Task save(Task task) {
        var entity = taskMapper.toEntity(task);
        var saved = taskJpaRepository.save(entity);
        return taskMapper.toDomain(saved);
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskJpaRepository.findById(id)
                .map(taskMapper::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        taskJpaRepository.deleteById(id);
    }
}
