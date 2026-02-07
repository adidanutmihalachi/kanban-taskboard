package com.adi.taskboard.infrastructure.adapter.in.web.dto;

import lombok.Data;

@Data
public class CreateBoardRequest {
    private String title;
    private String description;
}
