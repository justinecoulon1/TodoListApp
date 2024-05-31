package com.coulon.todo.app.common.dto;

public class TodoListElementDto {

    private Long id;
    private String description;
    private TodoListElementStatus status;

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

    public TodoListElementStatus getStatus() {
        return status;
    }

    public void setStatus(TodoListElementStatus status) {
        this.status = status;
    }

}
