package com.adi.taskboard.infrastructure.adapter.in.web.mapper;

import com.adi.taskboard.domain.model.Board;
import com.adi.taskboard.domain.model.Column;
import com.adi.taskboard.domain.model.Priority;
import com.adi.taskboard.domain.model.Task;
import com.adi.taskboard.infrastructure.adapter.in.web.dto.response.BoardResponse;
import com.adi.taskboard.infrastructure.adapter.in.web.dto.response.ColumnResponse;
import com.adi.taskboard.infrastructure.adapter.in.web.dto.response.TaskResponse;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardDtoMapperTest {

    private final BoardDtoMapper mapper = new BoardDtoMapper();

    @Test
    void toBoardResponseReturnsNullWhenInputNull() {
        assertNull(mapper.toBoardResponse(null));
    }

    @Test
    void toBoardResponsePopulatesColumnsAndTasks() {
        Task task = Task.builder().id(1L).title("Task").priority(Priority.HIGH).build();
        Column column = Column.builder()
                .id(2L)
                .title("Col")
                .position(1)
                .tasks(List.of(task))
                .build();
        Board board = Board.builder()
                .id(3L)
                .title("Board")
                .columns(List.of(column))
                .build();

        BoardResponse response = mapper.toBoardResponse(board);

        assertNotNull(response);
        assertEquals(board.getTitle(), response.getTitle());
        assertEquals(1, response.getColumns().size());
        ColumnResponse columnResponse = response.getColumns().get(0);
        assertEquals(column.getTitle(), columnResponse.getTitle());
        TaskResponse taskResponse = columnResponse.getTasks().get(0);
        assertEquals(task.getId(), taskResponse.getId());
        assertEquals(task.getPriority(), taskResponse.getPriority());
    }

    @Test
    void toColumnResponseReturnsEmptyListForNullTasks() {
        Column column = Column.builder().id(4L).tasks(null).build();
        var response = mapper.toColumnResponse(column);
        assertNotNull(response.getTasks());
        assertTrue(response.getTasks().isEmpty());
    }

    @Test
    void toTaskResponseHandlesNullsGracefully() {
        TaskResponse response = mapper.toTaskResponse(null);
        assertNull(response);
    }
}

