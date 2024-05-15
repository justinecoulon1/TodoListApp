package com.coulon.todo.app.common.dto;

public class CreateTodoListRequestDto {

    private String todoListName;
    private String userName;

    public CreateTodoListRequestDto() {
    }

    public CreateTodoListRequestDto(String todoListName, String userName) {
        this.todoListName = todoListName;
        this.userName = userName;
    }

    public String getTodoListName() {
        return todoListName;
    }

    public void setTodoListName(String todoListName) {
        this.todoListName = todoListName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
