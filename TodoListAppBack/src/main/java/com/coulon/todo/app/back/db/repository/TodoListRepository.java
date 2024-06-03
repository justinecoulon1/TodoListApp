package com.coulon.todo.app.back.db.repository;

import com.coulon.todo.app.back.db.model.TodoList;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface TodoListRepository extends ListCrudRepository<TodoList, Long> {

    List<TodoList> findAllByOrderByIdAsc();

}
