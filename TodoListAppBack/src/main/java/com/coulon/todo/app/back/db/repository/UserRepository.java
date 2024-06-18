package com.coulon.todo.app.back.db.repository;

import com.coulon.todo.app.back.db.model.User;
import org.springframework.data.repository.ListCrudRepository;

public interface UserRepository extends ListCrudRepository<User, Long> {

    User findByEmailIgnoreCase(String email);
    User findBySessionToken(String sessionToken);

}
