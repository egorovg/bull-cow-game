package ru.example.bullcowgame.service;

import org.springframework.http.ResponseEntity;
import ru.example.bullcowgame.entity.User;

import java.io.IOException;
import java.util.List;

/**
 * user service
 */
public interface UserService {
    /**
     * find user by username
     * @param username
     * @return user
     */
    User findByUsername(String username);

    /**
     * find all users
     * @return list of users
     */
    List<User> findAll();

    /**
     * save user in database
     * @param user
     * @return user
     */
    User save(User user);

    /**
     * register user
     * @param profileJson
     * @return response entity
     */
    ResponseEntity registration(String profileJson) throws IOException;
}

