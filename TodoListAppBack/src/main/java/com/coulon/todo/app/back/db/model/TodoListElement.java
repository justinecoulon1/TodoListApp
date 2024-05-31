package com.coulon.todo.app.back.db.model;

import com.coulon.todo.app.common.dto.TodoListElementStatus;
import jakarta.persistence.*;

@Entity(name = "todolistelement")
public class TodoListElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    private String description;
    @Enumerated(value = EnumType.STRING)
    private TodoListElementStatus status;
    @ManyToOne
    @JoinColumn(name = "todolistid", referencedColumnName = "id")
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

    public TodoListElementStatus getStatus() {
        return status;
    }

    public void setStatus(TodoListElementStatus status) {
        this.status = status;
    }

}
