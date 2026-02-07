package com.adi.taskboard.domain.port.in;

import com.adi.taskboard.domain.model.Task;

public interface MoveTaskUseCase {
    Task moveTask(Long taskId, Long targetColumnId, Integer newPosition);
}
