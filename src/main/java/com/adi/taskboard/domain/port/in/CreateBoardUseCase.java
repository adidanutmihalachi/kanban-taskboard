package com.adi.taskboard.domain.port.in;

import com.adi.taskboard.domain.model.Board;

public interface CreateBoardUseCase {
    Board create(String title, String description);
}
