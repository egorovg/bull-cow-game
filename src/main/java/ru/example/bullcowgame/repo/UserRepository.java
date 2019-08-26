package ru.example.bullcowgame.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.bullcowgame.entity.User;

import java.util.List;

/**
 * User repository
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * find user by username
     *
     * @param username
     * @return user
     */
    User findByUsername(String username);

    /**
     * find all users
     *
     * @return list of users
     */
    List<User> findAll();

    /**
     * save user in database
     *
     * @param user
     * @return user
     */
    User save(User user);

}