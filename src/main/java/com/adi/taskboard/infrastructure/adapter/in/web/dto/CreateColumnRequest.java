package com.adi.taskboard.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateColumnRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Board ID is required")
    private Long boardId;

    private Integer position;
}
