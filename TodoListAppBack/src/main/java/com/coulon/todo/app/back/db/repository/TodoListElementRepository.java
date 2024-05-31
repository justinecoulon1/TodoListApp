package com.coulon.todo.app.back.db.repository;

import com.coulon.todo.app.back.db.model.TodoListElement;
import org.springframework.data.repository.ListCrudRepository;

public interface TodoListElementRepository extends ListCrudRepository<TodoListElement, Long> {
}
