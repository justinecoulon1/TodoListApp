package com.coulon.todo.app.back.web.mappers;

import com.coulon.todo.app.back.db.model.TodoListElement;
import com.coulon.todo.app.common.dto.TodoListElementDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class TodoListElementMapper {

    public TodoListElementDto entityToDto(TodoListElement todoListElement) {
        TodoListElementDto todoListElementDto = new TodoListElementDto();
        todoListElementDto.setId(todoListElement.getId());
        todoListElementDto.setTodoListElementStatus(todoListElement.getTodoListElementStatus());
        todoListElementDto.setDescription(todoListElement.getDescription());
        return todoListElementDto;
    }

    public List<TodoListElementDto> entitiesToDtos(Collection<TodoListElement> todoListElements) {
        return todoListElements.stream().map(this::entityToDto).toList();
    }

}
