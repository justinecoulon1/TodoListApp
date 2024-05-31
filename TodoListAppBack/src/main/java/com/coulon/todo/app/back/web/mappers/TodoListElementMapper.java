package com.coulon.todo.app.back.web.mappers;

import com.coulon.todo.app.back.db.model.TodoListElement;
import com.coulon.todo.app.common.dto.TodoListElementDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class TodoListElementMapper {

    public TodoListElementDto entityToDto(TodoListElement todoListElement) {
        TodoListElementDto todoListElementDto = new TodoListElementDto();
        todoListElementDto.setId(todoListElement.getId());
        todoListElementDto.setStatus(todoListElement.getStatus());
        todoListElementDto.setDescription(todoListElement.getDescription());
        return todoListElementDto;
    }

    public List<TodoListElementDto> entitiesToDtos(Collection<TodoListElement> todoListElements) {
        return todoListElements.stream().map(this::entityToDto).toList();
    }

    public TodoListElement dtoToEntity(TodoListElementDto todoListElementDto) {
        TodoListElement todoListElement = new TodoListElement();
        todoListElement.setId(todoListElementDto.getId());
        todoListElement.setStatus(todoListElementDto.getStatus());
        todoListElement.setDescription(todoListElementDto.getDescription());
        return todoListElement;
    }

    public List<TodoListElement> dtosToEntities(Collection<TodoListElementDto> todoListElementDtos) {
        return new ArrayList<>(todoListElementDtos.stream().map(this::dtoToEntity).toList());
    }


}
