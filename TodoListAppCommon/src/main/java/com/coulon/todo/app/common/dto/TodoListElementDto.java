package com.coulon.todo.app.common.dto;

public class TodoListElementDto {

    private Long id;
    private String description;
    private TodoListElementStatus todoListElementStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TodoListElementStatus getTodoListElementStatus() {
        return todoListElementStatus;
    }

    public void setTodoListElementStatus(TodoListElementStatus todoListElementStatus) {
        this.todoListElementStatus = todoListElementStatus;
    }

}
