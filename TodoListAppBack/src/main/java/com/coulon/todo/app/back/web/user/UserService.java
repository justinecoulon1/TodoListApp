package com.coulon.todo.app.back.web.user;

import com.coulon.todo.app.back.db.model.User;
import com.coulon.todo.app.back.db.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByName(String name) {
        return userRepository.findByName(name);
    }

    public User createUser(String name) {
        User user = new User();
        user.setName(name);
        return userRepository.save(user);
    }

    public User getOrCreateUser(String name) {
        User user = getUserByName(name);
        if (user != null) {
            return user;
        }
        return createUser(name);
    }

}
