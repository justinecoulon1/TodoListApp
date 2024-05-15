package com.coulon.todo.app.common.dto;

import java.util.List;

public class TodoListDto {

    private String name;
    private long id;
    private List<TodoListElementDto> todoListElementDtos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<TodoListElementDto> getTodoListElementDtos() {
        return todoListElementDtos;
    }

    public void setTodoListElementDtos(List<TodoListElementDto> todoListElementsDto) {
        this.todoListElementDtos = todoListElementsDto;
    }

}
