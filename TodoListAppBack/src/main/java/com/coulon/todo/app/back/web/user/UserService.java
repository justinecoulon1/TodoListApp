package com.coulon.todo.app.back.web.user;

import com.coulon.todo.app.back.db.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private final Map<Long, User> userById = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public User getUserByName(String name) {
        return userById.values().stream()
                .filter(user -> user.getName().equals(name))
                .findFirst().orElse(null);
    }

    public User getOrCreateUser(String name) {
        return userById.values().stream()
                .filter(user -> user.getName().equals(name))
                .findFirst().orElseGet(() -> createUser(name));
    }

    public User createUser(String name) {
        return new User(name, idGenerator.getAndIncrement());
    }

}
