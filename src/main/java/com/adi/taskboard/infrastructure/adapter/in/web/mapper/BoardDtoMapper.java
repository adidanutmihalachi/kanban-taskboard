package com.adi.taskboard.infrastructure.adapter.in.web.mapper;

import com.adi.taskboard.domain.model.Board;
import com.adi.taskboard.domain.model.Column;
import com.adi.taskboard.domain.model.Task;
import com.adi.taskboard.infrastructure.adapter.in.web.dto.response.BoardResponse;
import com.adi.taskboard.infrastructure.adapter.in.web.dto.response.ColumnResponse;
import com.adi.taskboard.infrastructure.adapter.in.web.dto.response.TaskResponse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class BoardDtoMapper {

    public BoardResponse toBoardResponse(Board board) {
        if (board == null) return null;

        return BoardResponse.builder()
                .id(board.getId())
                .title(board.getTitle())
                .description(board.getDescription())
                .columns(board.getColumns() != null ?
                        board.getColumns().stream()
                                .map(this::toColumnResponse)
                                .collect(Collectors.toList()) :
                        Collections.emptyList())
                .createdAt(board.getCreatedAt())
                .build();
    }

    public ColumnResponse toColumnResponse(Column column) {
        if (column == null) return null;

        return ColumnResponse.builder()
                .id(column.getId())
                .title(column.getTitle())
                .position(column.getPosition())
                .tasks(column.getTasks() != null ?
                        column.getTasks().stream()
                                .map(this::toTaskResponse)
                                .collect(Collectors.toList()) :
                        Collections.emptyList())
                .build();
    }

    public TaskResponse toTaskResponse(Task task) {
        if (task == null) return null;

        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .position(task.getPosition())
                .priority(task.getPriority())
                .createdAt(task.getCreatedAt())
                .build();
    }
}
