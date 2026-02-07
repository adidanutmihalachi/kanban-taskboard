package com.adi.taskboard.infrastructure.adapter.in.web.dto;

import com.adi.taskboard.domain.model.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTaskRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Column ID is required")
    private Long columnId;

    private Priority priority;
}
