package com.adi.taskboard.infrastructure.adapter.in.web.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ColumnResponse {
    private Long id;
    private String title;
    private Integer position;
    private List<TaskResponse> tasks;
}
