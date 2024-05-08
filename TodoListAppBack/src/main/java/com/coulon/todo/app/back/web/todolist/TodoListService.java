package com.coulon.todo.app.back.web.todolist;

import com.coulon.todo.app.back.db.model.TodoList;
import com.coulon.todo.app.back.db.model.TodoListElement;
import com.coulon.todo.app.back.db.model.TodoListElementStatus;
import com.coulon.todo.app.back.db.model.User;
import com.coulon.todo.app.back.web.user.UserService;
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

    public TodoList deleteTodoList(Long id) {
        return todoListById.remove(id);
    }

    public List<TodoList> getAllTodoList() {
        return new ArrayList<>(todoListById.values());
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

    public TodoListElement createTodoListElement(TodoList todoList) {
        TodoListElement todoListElement = new TodoListElement();
        todoListElement.setId(todoListElementIdGenerator.getAndIncrement());
        todoListElement.setDescription("");
        todoListElement.setTodoListElementStatus(TodoListElementStatus.NOT_DONE);
        todoListElement.setTodoList(todoList);
        todoList.getTodoListElements().add(todoListElement);
        return todoListElement;
    }

    public TodoListElement deleteTodoListElement(Long id) {
        TodoListElement deletedTodoListElement = todoListElementById.remove(id);
        if (deletedTodoListElement != null) {
            deletedTodoListElement.getTodoList().getTodoListElements().remove(deletedTodoListElement);
        }
        return deletedTodoListElement;
    }

}
