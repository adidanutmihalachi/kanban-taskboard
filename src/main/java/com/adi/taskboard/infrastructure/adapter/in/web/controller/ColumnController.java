package com.adi.taskboard.infrastructure.adapter.in.web.controller;

import com.adi.taskboard.domain.port.in.CreateColumnUseCase;
import com.adi.taskboard.infrastructure.adapter.in.web.dto.CreateColumnRequest;
import com.adi.taskboard.infrastructure.adapter.in.web.dto.response.ColumnResponse;
import com.adi.taskboard.infrastructure.adapter.in.web.mapper.BoardDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/columns")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ColumnController {

    private final CreateColumnUseCase createColumnUseCase;
    private final BoardDtoMapper mapper;

    @PostMapping
    public ResponseEntity<ColumnResponse> createColumn(@Valid @RequestBody CreateColumnRequest request) {
        var column = createColumnUseCase.create(
                request.getTitle(),
                request.getBoardId(),
                request.getPosition()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toColumnResponse(column));
    }
}
