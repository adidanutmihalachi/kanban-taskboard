package com.adi.taskboard.domain.port.in;

import com.adi.taskboard.domain.model.Column;

public interface CreateColumnUseCase {
    Column create(String title, Long boardId, Integer position);
}
