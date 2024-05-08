package com.coulon.todo.app.back.db.model;

public class TodoListElement {

    private Long id;
    private String description;
    private TodoListElementStatus todoListElementStatus;
    private TodoList todoList;

    public TodoListElement() {
    }

    public TodoList getTodoList() {
        return todoList;
    }

    public void setTodoList(TodoList todoList) {
        this.todoList = todoList;
    }

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
