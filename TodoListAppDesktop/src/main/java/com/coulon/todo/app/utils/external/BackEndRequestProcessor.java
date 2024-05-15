package com.coulon.todo.app.utils.external;

import com.coulon.todo.app.common.dto.CreateTodoListRequestDto;
import com.coulon.todo.app.common.dto.TodoListDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class BackEndRequestProcessor {

    public static final BackEndRequestProcessor INSTANCE = new BackEndRequestProcessor();

    private BackEndRequestProcessor() {
    }

    private final HttpClient client = HttpClient.newBuilder()
            .build();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<TodoListDto> getAllTodoLists() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/todo_list/all"))
                    .GET()
                    .build();
            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
            return objectMapper.readValue(response.body(), new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TodoListDto getTodoListById(Long id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8080/todo_list/" + id))
                    .GET()
                    .build();
            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
            return objectMapper.readValue(response.body(), TodoListDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TodoListDto createTodoList(String todoListName, String userName) {
        try {
            CreateTodoListRequestDto body = new CreateTodoListRequestDto();
            body.setTodoListName(todoListName);
            body.setUserName(userName);

            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(new URI("http://localhost:8080/todo_list"))
                    .POST(HttpRequest.BodyPublishers.ofByteArray(objectMapper.writeValueAsBytes(body)))
                    .build();

            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Error creating todo list : " + new String(response.body()));
            }
            return objectMapper.readValue(response.body(), TodoListDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TodoListDto deleteTodoList(Long id) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(new URI("http://localhost:8080/todo_list/" + id + "/delete"))
                    .POST(HttpRequest.BodyPublishers.ofString("{}"))
                    .build();

            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Error deleting todo list : " + new String(response.body()));
            }
            return objectMapper.readValue(response.body(), TodoListDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TodoListDto updateTodoList(TodoListDto todoListDto) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(new URI("http://localhost:8080/todo_list/update"))
                    .POST(HttpRequest.BodyPublishers.ofByteArray(objectMapper.writeValueAsBytes(todoListDto)))
                    .build();

            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
            if (response.statusCode() != 200) {
                throw new RuntimeException("Error updating todo list : " + new String(response.body()));
            }
            return objectMapper.readValue(response.body(), TodoListDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}