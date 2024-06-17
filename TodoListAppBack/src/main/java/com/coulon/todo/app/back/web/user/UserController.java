package com.coulon.todo.app.back.web.user;

import com.coulon.todo.app.common.dto.LogInRequestDto;
import com.coulon.todo.app.common.dto.SessionDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public SessionDto logInUser(@RequestBody LogInRequestDto logInRequestDto) {
        String token = userService.logInUser(logInRequestDto.getEmail(), logInRequestDto.getPassword());
        return new SessionDto(token);
    }

}
