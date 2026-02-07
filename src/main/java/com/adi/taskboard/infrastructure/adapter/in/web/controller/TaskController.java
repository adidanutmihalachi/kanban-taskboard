package com.adi.taskboard.infrastructure.adapter.in.web.controller;

import com.adi.taskboard.domain.port.in.CreateTaskUseCase;
import com.adi.taskboard.domain.port.in.MoveTaskUseCase;
import com.adi.taskboard.infrastructure.adapter.in.web.dto.CreateTaskRequest;
import com.adi.taskboard.infrastructure.adapter.in.web.dto.MoveTaskRequest;
import com.adi.taskboard.infrastructure.adapter.in.web.dto.response.TaskResponse;
import com.adi.taskboard.infrastructure.adapter.in.web.mapper.BoardDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TaskController {

    private final CreateTaskUseCase createTaskUseCase;
    private final MoveTaskUseCase moveTaskUseCase;
    private final BoardDtoMapper mapper;

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request) {
        var task = createTaskUseCase.create(
                request.getTitle(),
                request.getDescription(),
                request.getColumnId(),
                request.getPriority()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toTaskResponse(task));
    }

    @PutMapping("/move")
    public ResponseEntity<TaskResponse> moveTask(@Valid @RequestBody MoveTaskRequest request) {
        var task = moveTaskUseCase.moveTask(
                request.getTaskId(),
                request.getTargetColumnId(),
                request.getNewPosition()
        );
        return ResponseEntity.ok(mapper.toTaskResponse(task));
    }
}
