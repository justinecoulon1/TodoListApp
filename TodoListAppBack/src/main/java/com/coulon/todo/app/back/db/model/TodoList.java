package com.coulon.todo.app.back.db.model;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "todolist")
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "id")
    private User user;
    private String name;
    @OneToMany(mappedBy = "todoList", cascade = CascadeType.ALL)
    private List<TodoListElement> todoListElements;

    public TodoList() {
    }

    public TodoList(User user, String name, List<TodoListElement> todoListElements) {
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
