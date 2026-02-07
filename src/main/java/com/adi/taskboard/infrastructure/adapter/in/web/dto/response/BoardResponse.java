package com.adi.taskboard.infrastructure.adapter.in.web.dto.response;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BoardResponse {
    private Long id;
    private String title;
    private String description;
    private List<ColumnResponse> columns;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
