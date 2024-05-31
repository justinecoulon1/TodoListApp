package com.coulon.todo.app.back.web.user;

import com.coulon.todo.app.back.db.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getUserByName(@RequestParam String name) {
       return userService.getUserByName(name);
    }

    @PostMapping
    public User getOrCreateUser(@RequestParam String name) {
        return userService.getOrCreateUser(name);
    }

}
