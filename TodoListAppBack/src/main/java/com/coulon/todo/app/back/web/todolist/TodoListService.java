package com.coulon.todo.app.back.web.todolist;

import com.coulon.todo.app.back.db.model.TodoList;
import com.coulon.todo.app.back.db.model.TodoListElement;
import com.coulon.todo.app.back.db.model.User;
import com.coulon.todo.app.back.db.repository.TodoListElementRepository;
import com.coulon.todo.app.back.db.repository.TodoListRepository;
import com.coulon.todo.app.back.web.user.UserService;
import com.coulon.todo.app.common.dto.TodoListElementDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodoListService {

    private final UserService userService;
    private final TodoListRepository todoListRepository;
    private final TodoListElementRepository todoListElementRepository;

    public TodoListService(UserService userService, TodoListRepository todoListRepository, TodoListElementRepository todoListElementRepository) {
        this.userService = userService;
        this.todoListRepository = todoListRepository;
        this.todoListElementRepository = todoListElementRepository;
    }

    public TodoList createTodoList(String todoListName, String userName) {
        User user = userService.getOrCreateUser(userName);
        TodoList todoList = new TodoList(user, todoListName, new ArrayList<>());
        return todoListRepository.save(todoList);
    }

    public TodoList updateTodoList(String todoListName, Long id, List<TodoListElement> todoListElements) {
        TodoList todoList = todoListRepository.findById(id).orElseThrow();
        List<Long> existingElementIds = todoList.getTodoListElements().stream().map(TodoListElement::getId).toList();
        List<TodoListElement> newValidTodoListElements = todoListElements.stream()
                .filter(todoListElement -> todoListElement.getId() == null || existingElementIds.contains(todoListElement.getId()))
                .toList();
        for (TodoListElement todoListElement : newValidTodoListElements) {
            todoListElement.setTodoList(todoList);
        }
        todoList.setName(todoListName);
        todoList.setTodoListElements(new ArrayList<>(newValidTodoListElements));
        TodoList savedTodoList = todoListRepository.save(todoList);

        List<Long> newElementIds = savedTodoList.getTodoListElements().stream().map(TodoListElement::getId).toList();
        List<Long> toDeleteElementIds = existingElementIds.stream().filter(todoListElementId -> !newElementIds.contains(todoListElementId)).toList();
        todoListElementRepository.deleteAllById(toDeleteElementIds);

        return savedTodoList;
    }

    public void deleteTodoList(Long id) {
        todoListRepository.deleteById(id);
    }

    public List<TodoList> getAllTodoList() {
        return todoListRepository.findAll();
    }

    public TodoList getTodoListById(Long id) {
        return todoListRepository.findById(id).orElse(null);
    }

    public TodoList updateTodoListElement(TodoListElementDto todoListElementDto) {
        TodoListElement todoListElement = todoListElementRepository.findById(todoListElementDto.getId()).orElseThrow();
        todoListElement.setDescription(todoListElementDto.getDescription());
        todoListElement.setStatus(todoListElementDto.getStatus());
        return todoListElementRepository.save(todoListElement).getTodoList();
    }

}
