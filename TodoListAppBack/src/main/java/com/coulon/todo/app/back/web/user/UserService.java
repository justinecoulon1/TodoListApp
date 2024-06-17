package com.coulon.todo.app.back.web.user;

import com.coulon.todo.app.back.db.model.User;
import com.coulon.todo.app.back.db.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(String email) {
        User user = new User();
        user.setEmail(email);
        return userRepository.save(user);
    }

    public User getUserBySessionToken(String sessionToken) {
        return userRepository.findBySessionToken(sessionToken);
    }

    public String logInUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User does not exist with email: " + email);
        }
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }
        user.setSessionToken(UUID.randomUUID().toString());
        return userRepository.save(user).getSessionToken();
    }

}
