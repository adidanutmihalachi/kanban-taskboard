package com.adi.taskboard.infrastructure.adapter.in.web.dto.response;

import com.adi.taskboard.domain.model.Priority;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private Integer position;
    private Priority priority;
    private LocalDateTime createdAt;
}
