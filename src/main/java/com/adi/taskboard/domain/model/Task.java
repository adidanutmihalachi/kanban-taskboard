package com.adi.taskboard.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    private Long id;
    private String title;
    private String description;
    private Integer position;
    private Priority priority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void updatePosition(Integer newPosition) {
        this.position = newPosition;
    }
}