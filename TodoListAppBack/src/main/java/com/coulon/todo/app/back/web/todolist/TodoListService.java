package com.coulon.todo.app.back.web.todolist;

import com.coulon.todo.app.back.db.model.TodoList;
import com.coulon.todo.app.back.db.model.TodoListElement;
import com.coulon.todo.app.back.db.model.User;
import com.coulon.todo.app.back.web.user.UserService;
import com.coulon.todo.app.common.dto.TodoListElementDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

@Service
public class TodoListService {

    private final Map<Long, TodoList> todoListById = new HashMap<>();
    private final Map<Long, TodoListElement> todoListElementById = new HashMap<>();
    private final AtomicLong todoListIdGenerator = new AtomicLong(1);
    private final AtomicLong todoListElementIdGenerator = new AtomicLong(1);
    private final UserService userService;

    public TodoListService(UserService userService) {
        this.userService = userService;
    }

    public TodoList createTodoList(String todoListName, String userName) {
        User user = userService.getOrCreateUser(userName);
        TodoList todoList = new TodoList(todoListIdGenerator.getAndIncrement(), user, todoListName, new ArrayList<>());
        todoListById.put(todoList.getId(), todoList);
        return todoList;
    }

    public TodoList updateTodoList(String todoListName, Long id, List<TodoListElementDto> todoListElementDtos) {
        TodoList todoList = todoListById.get(id);
        todoList.setName(todoListName);
        List<Long> existingElementIds = todoList.getTodoListElements().stream().map(TodoListElement::getId).toList();
        for (TodoListElementDto todoListElementDto : todoListElementDtos) {
            Long todoListElementDtoId = todoListElementDto.getId();
            if (todoListElementDtoId == null || !existingElementIds.contains(todoListElementDtoId)) {
                createTodoListElement(todoList, todoListElementDto);
            } else {
                updateTodoListElement(todoListElementDto);
            }
        }
        List<Long> newElementIds = todoListElementDtos.stream().map(TodoListElementDto::getId).toList();
        existingElementIds.stream().filter(todoListElementId -> !newElementIds.contains(todoListElementId)).forEach(this::deleteTodoListElement);
        return todoList;
    }

    public TodoList deleteTodoList(Long id) {
        return todoListById.remove(id);
    }

    public List<TodoList> getAllTodoList() {
        return new ArrayList<>(todoListById.values());
    }

    public TodoList getTodoListById(Long id) {
        return todoListById.get(id);
    }

    public List<TodoList> findTodoLists(String userName, String searchedName) {
        Stream<TodoList> todoListStream = todoListById.values().stream();
        if (userName != null) {
            User searchedUser = userService.getUserByName(userName);
            todoListStream = todoListStream.filter(todoList -> todoList.getUser().getId().equals(searchedUser.getId()));
        }
        if (searchedName != null) {
            String searchedNameLowerCase = searchedName.toLowerCase();
            todoListStream = todoListStream.filter(todoList -> todoList.getName().toLowerCase().contains(searchedNameLowerCase));
        }
        return todoListStream.toList();
    }

    private TodoListElement createTodoListElement(TodoList todoList, TodoListElementDto todoListElementDto) {
        TodoListElement todoListElement = new TodoListElement();
        todoListElement.setId(todoListElementIdGenerator.getAndIncrement());
        todoListElement.setDescription(todoListElementDto.getDescription());
        todoListElement.setTodoListElementStatus(todoListElementDto.getTodoListElementStatus());
        todoListElement.setTodoList(todoList);
        todoList.getTodoListElements().add(todoListElement);
        todoListElementById.put(todoListElement.getId(), todoListElement);
        return todoListElement;
    }

    private TodoListElement deleteTodoListElement(Long id) {
        TodoListElement deletedTodoListElement = todoListElementById.remove(id);
        if (deletedTodoListElement != null) {
            deletedTodoListElement.getTodoList().getTodoListElements().remove(deletedTodoListElement);
        }
        return deletedTodoListElement;
    }

    public TodoListElement updateTodoListElement(TodoListElementDto todoListElementDto) {
        TodoListElement todoListElement = todoListElementById.get(todoListElementDto.getId());
        todoListElement.setDescription(todoListElementDto.getDescription());
        todoListElement.setTodoListElementStatus(todoListElementDto.getTodoListElementStatus());
        return todoListElement;
    }


}
