package com.coulon.todo.app.utils.external;

import com.coulon.todo.app.common.dto.CreateTodoListRequestDto;
import com.coulon.todo.app.common.dto.TodoListDto;
import com.coulon.todo.app.common.dto.TodoListElementDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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


    private HttpRequest buildGetRequest(String url) {
        try {
            return HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(new URI(url))
                    .GET()
                    .build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpRequest buildPostRequest(String url, Object body) {
        try {
            return HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .uri(new URI(url))
                    .POST(HttpRequest.BodyPublishers.ofByteArray(objectMapper.writeValueAsBytes(body)))
                    .build();
        } catch (URISyntaxException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpResponse<byte[]> getResponse(HttpRequest request) {
        try {
            HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
            int statusCode = response.statusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Error code (" + statusCode + "): " + new String(response.body()));
            }
            return response;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpResponse<byte[]> sendGetRequest(String url) {
        return getResponse(buildGetRequest(url));
    }

    private HttpResponse<byte[]> sendPostRequest(String url, Object body) {
        return getResponse(buildPostRequest(url, body));
    }

    public List<TodoListDto> getAllTodoLists() {
        try {
            HttpResponse<byte[]> response = sendGetRequest("http://localhost:8080/todo_list/all");
            return objectMapper.readValue(response.body(), new TypeReference<>() {
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TodoListDto getTodoListById(Long id) {
        try {
            HttpResponse<byte[]> response = sendGetRequest("http://localhost:8080/todo_list/" + id);
            return objectMapper.readValue(response.body(), TodoListDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TodoListDto createTodoList(String todoListName, String userName) {
        CreateTodoListRequestDto body = new CreateTodoListRequestDto();
        body.setTodoListName(todoListName);
        body.setUserName(userName);
        try {
            HttpResponse<byte[]> response = sendPostRequest("http://localhost:8080/todo_list", body);
            return objectMapper.readValue(response.body(), TodoListDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TodoListDto deleteTodoList(Long id) {
        try {
            HttpResponse<byte[]> response = sendPostRequest("http://localhost:8080/todo_list/" + id + "/delete", new Object());
            return objectMapper.readValue(response.body(), TodoListDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TodoListDto updateTodoList(TodoListDto todoListDto) {
        try {
            HttpResponse<byte[]> response = sendPostRequest("http://localhost:8080/todo_list/update", todoListDto);
            return objectMapper.readValue(response.body(), TodoListDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TodoListElementDto updateTodoListElement(TodoListElementDto todoListElementDto) {
        try {
            HttpResponse<byte[]> response = sendPostRequest("http://localhost:8080/todo_list/element/update", todoListElementDto);
            return objectMapper.readValue(response.body(), TodoListElementDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}