package com.adi.taskboard.infrastructure.adapter.in.web.controller;

import com.adi.taskboard.domain.port.in.CreateBoardUseCase;
import com.adi.taskboard.domain.port.in.GetBoardUseCase;
import com.adi.taskboard.infrastructure.adapter.in.web.dto.CreateBoardRequest;
import com.adi.taskboard.infrastructure.adapter.in.web.dto.response.BoardResponse;
import com.adi.taskboard.infrastructure.adapter.in.web.mapper.BoardDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Para desarrollo, después restringe
public class BoardController {

    private final CreateBoardUseCase createBoardUseCase;
    private final GetBoardUseCase getBoardUseCase;
    private final BoardDtoMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> getBoard(@PathVariable Long id) {
        var board = getBoardUseCase.getById(id);
        return ResponseEntity.ok(mapper.toBoardResponse(board));
    }

    @PostMapping
    public ResponseEntity<BoardResponse> createBoard(@Valid @RequestBody CreateBoardRequest request) {
        var board = createBoardUseCase.create(request.getTitle(), request.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toBoardResponse(board));
    }
}
