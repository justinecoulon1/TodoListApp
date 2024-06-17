package com.coulon.todo.app.back.web.todolist;

import com.coulon.todo.app.back.db.model.TodoList;
import com.coulon.todo.app.back.db.model.TodoListElement;
import com.coulon.todo.app.back.db.model.User;
import com.coulon.todo.app.back.db.repository.TodoListElementRepository;
import com.coulon.todo.app.back.db.repository.TodoListRepository;
import com.coulon.todo.app.common.dto.TodoListElementDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TodoListService {

    private final TodoListRepository todoListRepository;
    private final TodoListElementRepository todoListElementRepository;

    public TodoListService(TodoListRepository todoListRepository, TodoListElementRepository todoListElementRepository) {
        this.todoListRepository = todoListRepository;
        this.todoListElementRepository = todoListElementRepository;
    }

    public TodoList createTodoList(String todoListName, User user) {
        if (user == null) {
            throw new RuntimeException("User needed to create a todo list");
        }
        TodoList todoList = new TodoList(user, todoListName, new ArrayList<>());
        return todoListRepository.save(todoList);
    }

    public TodoList updateTodoList(String todoListName, Long id, List<TodoListElement> todoListElements, User user) {
        if (user == null) {
            throw new RuntimeException("User needed to update a todo list");
        }

        TodoList todoList = todoListRepository.findById(id).orElseThrow();

        if (!Objects.equals(todoList.getUser().getId(), user.getId())) {
            throw new RuntimeException("This user does not own this todo list");
        }

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

    public void deleteTodoList(Long id, User user) {
        TodoList todoList = todoListRepository.findById(id).orElseThrow();
        if (!Objects.equals(todoList.getUser().getId(), user.getId())) {
            throw new RuntimeException("This user does not own this todo list");
        }
        todoListRepository.deleteById(id);
    }

    public List<TodoList> getAllTodoList(User user) {
        return user.getTodoLists();
    }

    public TodoList getTodoListById(Long id, User user) {
        TodoList todoList = todoListRepository.findById(id).orElseThrow();
        if (!Objects.equals(todoList.getUser().getId(), user.getId())) {
            throw new RuntimeException("This user does not own this todo list");
        }
        return todoListRepository.findById(id).orElse(null);
    }

    public TodoList updateTodoListElement(TodoListElementDto todoListElementDto, User user) {
        TodoListElement todoListElement = todoListElementRepository.findById(todoListElementDto.getId()).orElseThrow();
        if (!Objects.equals(todoListElement.getTodoList().getUser().getId(), user.getId())) {
            throw new RuntimeException("This user does not own this todo list");
        }

        todoListElement.setDescription(todoListElementDto.getDescription());
        todoListElement.setStatus(todoListElementDto.getStatus());
        return todoListElementRepository.save(todoListElement).getTodoList();
    }

}
