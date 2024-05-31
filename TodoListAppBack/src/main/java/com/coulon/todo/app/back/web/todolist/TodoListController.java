package com.coulon.todo.app.back.web.todolist;

import com.coulon.todo.app.back.db.model.TodoList;
import com.coulon.todo.app.back.db.model.TodoListElement;
import com.coulon.todo.app.back.web.mappers.TodoListElementMapper;
import com.coulon.todo.app.back.web.mappers.TodoListMapper;
import com.coulon.todo.app.common.dto.CreateTodoListRequestDto;
import com.coulon.todo.app.common.dto.TodoListDto;
import com.coulon.todo.app.common.dto.TodoListElementDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo_list")
public class TodoListController {

    private final TodoListMapper todoListMapper;
    private final TodoListElementMapper todoListElementMapper;
    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService, TodoListMapper todoListMapper, TodoListElementMapper todoListElementMapper) {
        this.todoListService = todoListService;
        this.todoListMapper = todoListMapper;
        this.todoListElementMapper = todoListElementMapper;
    }

    @PostMapping
    public TodoListDto createTodoList(@RequestBody CreateTodoListRequestDto createTodoListRequestDto) {
        String todoListName = createTodoListRequestDto.getTodoListName();
        String todoListUserName = createTodoListRequestDto.getUserName();
        TodoList todoList = todoListService.createTodoList(todoListName, todoListUserName);
        return todoListMapper.entityToDto(todoList);
    }

    @PostMapping("/update")
    public TodoListDto updateTodoList(@RequestBody TodoListDto todoListDto) {
        String todoListName = todoListDto.getName();
        Long id = todoListDto.getId();
        List<TodoListElementDto> todoListElementDtos = todoListDto.getTodoListElementDtos();
        List<TodoListElement> todoListElements = todoListElementMapper.dtosToEntities(todoListElementDtos);
        return todoListMapper.entityToDto(todoListService.updateTodoList(todoListName, id, todoListElements));
    }

    @PostMapping("/{id}/delete")
    public void deleteTodoList(@PathVariable Long id) {
        todoListService.deleteTodoList(id);
    }

    @GetMapping("/{id}")
    public TodoListDto getTodoListById(@PathVariable Long id) {
        return todoListMapper.entityToDto(todoListService.getTodoListById(id));
    }

    @GetMapping("/all")
    public List<TodoListDto> getAllTodoList() {
        return todoListMapper.entitiesToDtos(todoListService.getAllTodoList());
    }

    @PostMapping("/element/update")
    public TodoListDto updateTodoListElement(@RequestBody TodoListElementDto todoListElementDto) {
        return todoListMapper.entityToDto(todoListService.updateTodoListElement(todoListElementDto));
    }

}
