package com.coulon.todo.app.back.web.todolist;

import com.coulon.todo.app.back.db.model.TodoList;
import com.coulon.todo.app.back.db.model.TodoListElement;
import com.coulon.todo.app.back.web.mappers.TodoListMapper;
import com.coulon.todo.app.back.web.todolist.dto.CreateTodoListRequestDto;
import com.coulon.todo.app.back.web.todolist.dto.TodoListDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo_list")
public class TodoListController {

    private final TodoListMapper todoListMapper;
    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService, TodoListMapper todoListMapper) {
        this.todoListService = todoListService;
        this.todoListMapper = todoListMapper;
    }

    @PostMapping
    public TodoListDto createTodoList(@RequestBody CreateTodoListRequestDto createTodoListRequestDto) { //comment inclure les dto
        String todoListName = createTodoListRequestDto.getTodoListName();
        String todoListUserName = createTodoListRequestDto.getUserName();
        TodoList todoList = todoListService.createTodoList(todoListName, todoListUserName);
        return todoListMapper.entityToDto(todoList);
    }

    @PostMapping("/{id}/delete")
    public TodoListDto deleteTodoList(@PathVariable Long id) { //comment inclure les dto
        TodoList deletedTodoList = todoListService.deleteTodoList(id);
        return todoListMapper.entityToDto(deletedTodoList);
    }

    @GetMapping
    public List<TodoListDto> getTodoLists(@RequestParam String userName, @RequestParam String searchedName) { //comment inclure les dto
        return todoListMapper.entitiesToDtos(todoListService.findTodoLists(userName, searchedName));
    }

    @GetMapping("/all")
    public List<TodoListDto> getAllTodoList() { //comment inclure les dto
        return todoListMapper.entitiesToDtos(todoListService.getAllTodoList());
    }

    @PostMapping("/element")
    public TodoListElement createTodoListElement(TodoList todoList) {
        return todoListService.createTodoListElement(todoList);
    }

    @PostMapping("/element/{id}/delete")
    public TodoListElement deleteTodoListElement(Long id) {
        return todoListService.deleteTodoListElement(id);
    }

}
