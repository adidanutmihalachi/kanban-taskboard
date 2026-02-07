package com.adi.taskboard.infrastructure.adapter.in.web;

import com.adi.taskboard.domain.model.Board;
import com.adi.taskboard.domain.port.in.CreateBoardUseCase;
import com.adi.taskboard.domain.port.in.GetBoardUseCase;
import com.adi.taskboard.infrastructure.adapter.in.web.dto.CreateBoardRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final CreateBoardUseCase createBoardUseCase;
    private final GetBoardUseCase getBoardUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoard(@PathVariable Long id) {
        return ResponseEntity.ok(getBoardUseCase.getById(id));
    }

    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody CreateBoardRequest request) {
        var board = createBoardUseCase.create(request.getTitle(), request.getDescription());
        return ResponseEntity.ok(board);
    }
}
