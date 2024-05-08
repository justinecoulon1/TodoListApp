package com.coulon.todo.app.back.db.model;

import java.util.List;

public class TodoList {

    private Long id;
    private User user;
    private String name;
    private List<TodoListElement> todoListElements;

    public TodoList() {
    }

    public TodoList(Long id, User user, String name, List<TodoListElement> todoListElements) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.todoListElements = todoListElements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TodoListElement> getTodoListElements() {
        return todoListElements;
    }

    public void setTodoListElements(List<TodoListElement> todoListElements) {
        this.todoListElements = todoListElements;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
