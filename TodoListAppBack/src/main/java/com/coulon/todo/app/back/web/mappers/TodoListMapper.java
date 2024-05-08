package com.coulon.todo.app.back.web.mappers;

import com.coulon.todo.app.back.db.model.TodoList;
import com.coulon.todo.app.back.web.todolist.dto.TodoListDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class TodoListMapper {

    public TodoListDto entityToDto(TodoList todoList) {
        TodoListDto dto = new TodoListDto();
        dto.setId(todoList.getId());
        dto.setName(todoList.getName());
        return dto;
    }

    public List<TodoListDto> entitiesToDtos(Collection<TodoList> todoLists) {
        return todoLists.stream().map(this::entityToDto).toList();
    }

}
