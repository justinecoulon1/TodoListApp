package com.coulon.todo.app.common.dto;

public class CreateTodoListRequestDto {

    private String todoListName;

    public CreateTodoListRequestDto() {
    }

    public CreateTodoListRequestDto(String todoListName) {
        this.todoListName = todoListName;
    }

    public String getTodoListName() {
        return todoListName;
    }

    public void setTodoListName(String todoListName) {
        this.todoListName = todoListName;
    }

}
