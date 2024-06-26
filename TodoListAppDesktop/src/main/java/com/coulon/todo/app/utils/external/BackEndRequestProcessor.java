package com.coulon.todo.app.utils.external;

import com.coulon.todo.app.common.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class BackEndRequestProcessor {

    public static final BackEndRequestProcessor INSTANCE = new BackEndRequestProcessor();
    public static String SESSION_TOKEN = "";

    private final HttpClient client = HttpClient.newBuilder().build();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private BackEndRequestProcessor() {
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    private HttpRequest buildGetRequest(String url) {
        try {
            return HttpRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Session-Token", SESSION_TOKEN)
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
                    .header("Session-Token", SESSION_TOKEN)
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

    public TodoListDto createTodoList(String todoListName) {
        CreateTodoListRequestDto body = new CreateTodoListRequestDto();
        body.setTodoListName(todoListName);
        try {
            HttpResponse<byte[]> response = sendPostRequest("http://localhost:8080/todo_list", body);
            return objectMapper.readValue(response.body(), TodoListDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteTodoList(Long id) {
        try {
            sendPostRequest("http://localhost:8080/todo_list/" + id + "/delete", new Object());
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

    public TodoListDto updateTodoListElement(TodoListElementDto todoListElementDto) {
        try {
            HttpResponse<byte[]> response = sendPostRequest("http://localhost:8080/todo_list/element/update", todoListElementDto);
            return objectMapper.readValue(response.body(), TodoListDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SessionDto logIn(String email, String password) {
        try {
            LogInRequestDto logInRequestDto = new LogInRequestDto();
            logInRequestDto.setEmail(email);
            logInRequestDto.setPassword(password);
            HttpResponse<byte[]> response = sendPostRequest("http://localhost:8080/user/login", logInRequestDto);
            return objectMapper.readValue(response.body(), SessionDto.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}