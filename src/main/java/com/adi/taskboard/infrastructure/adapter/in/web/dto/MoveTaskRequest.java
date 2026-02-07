package com.adi.taskboard.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MoveTaskRequest {

    @NotNull(message = "Task ID is required")
    private Long taskId;

    @NotNull(message = "Target Column ID is required")
    private Long targetColumnId;

    @NotNull(message = "New position is required")
    private Integer newPosition;
}
