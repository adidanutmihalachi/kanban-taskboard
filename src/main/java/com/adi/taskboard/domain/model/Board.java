package com.adi.taskboard.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
    private Long id;
    private String title;
    private String description;
    private List<Column> columns;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public void addColumn(Column column) {
        this.columns.add(column);
    }
}
