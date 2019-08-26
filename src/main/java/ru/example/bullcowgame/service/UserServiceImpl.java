package ru.example.bullcowgame.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.example.bullcowgame.entity.Role;
import ru.example.bullcowgame.entity.User;
import ru.example.bullcowgame.model.UserDTO;
import ru.example.bullcowgame.repo.UserRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User registrationUser) {
        User user;
        if (userRepository.findByUsername(registrationUser.getUsername()) == null) {
            user = new User();
            user.setUsername(registrationUser.getUsername());
            user.setPassword(passwordEncoder.encode(registrationUser.getPassword()));
            user.setRoles(Collections.singleton(Role.USER));
            user.setCountAttempt(0);
            user.setCountGames(0);
        } else user = userRepository.findByUsername(registrationUser.getUsername());
        return userRepository.save(user);
    }

    public ResponseEntity registration(String profileJson) throws IOException {
        UserDTO profile = new ObjectMapper().readValue(profileJson, UserDTO.class);
        if (findByUsername(profile.getUsername()) != null) {
            return ResponseEntity.badRequest().body(null);
        }
        save(new User(profile.getUsername(), profile.getPassword()));
        return ResponseEntity.ok().body(null);
    }
}
