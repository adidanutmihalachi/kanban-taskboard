package com.adi.taskboard.domain.port.out;

import com.adi.taskboard.domain.model.Task;

import java.util.Optional;

public interface TaskRepositoryPort {
    Task save(Task task);
    Optional<Task> findById(Integer id);
    void deleteById(Integer id);
}
