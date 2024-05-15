package com.coulon.todo.app.back.web.mappers;

import com.coulon.todo.app.back.db.model.TodoList;
import com.coulon.todo.app.back.db.model.TodoListElement;
import com.coulon.todo.app.common.dto.TodoListDto;
import com.coulon.todo.app.common.dto.TodoListElementDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class TodoListMapper {

    private final TodoListElementMapper todoListElementMapper;

    public TodoListMapper(TodoListElementMapper todoListElementMapper) {
        this.todoListElementMapper = todoListElementMapper;
    }

    public TodoListDto entityToDto(TodoList todoList) {
        TodoListDto todoListDto = new TodoListDto();
        todoListDto.setId(todoList.getId());
        todoListDto.setName(todoList.getName());
        List<TodoListElement> todoListElements = todoList.getTodoListElements();
        List<TodoListElementDto> todoListElementDtos = todoListElementMapper.entitiesToDtos(todoListElements);
        todoListDto.setTodoListElementDtos(todoListElementDtos);
        return todoListDto;
    }

    public List<TodoListDto> entitiesToDtos(Collection<TodoList> todoLists) {
        return todoLists.stream().map(this::entityToDto).toList();
    }

}
