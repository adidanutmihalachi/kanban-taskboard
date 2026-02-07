package com.adi.taskboard.domain.port.in;

import com.adi.taskboard.domain.model.Priority;
import com.adi.taskboard.domain.model.Task;

public interface CreateTaskUseCase {
    Task create(String title, String description, Long columnId, Priority priority);
}
