package com.adi.taskboard.domain.port.in;

import com.adi.taskboard.domain.model.Board;

public interface GetBoardUseCase {
    Board getById(Long id);
}
