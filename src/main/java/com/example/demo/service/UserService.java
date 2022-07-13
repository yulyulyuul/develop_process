package com.example.demo.service;

import com.example.demo.dto.request.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    @Autowired
    private final SessionManager sessionManager;

    @Autowired
    private final UserRepository userRepository;

    public void create(UserDto userDto) {

        String username = userDto.getUsername();
        String password = userDto.getPassword();

        if (usernameExists(username)) {throw new RuntimeException("username already exists");}
        if (isValid(username) && isValid(password)) {
            String encodedPassword = encodePassword(password);
            User user = User.builder()
                    .username(username)
                    .password(encodedPassword)
                    .build();
            userRepository.save(user);
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }

    public boolean usernameExists(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.isPresent();
    }

    private String encodePassword(String password) {
        StringBuilder encoded = new StringBuilder();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            byte[] data = messageDigest.digest();
            for (byte b : data) {
                encoded.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return encoded.toString();
    }

    private boolean isValid(String value) {
        return value.matches("[a-zA-Z_0-9]+");
    }

    public void login(UserDto userDto, HttpServletResponse response) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        String encodedPassword = encodePassword(password);

        if (!usernameExists(username)) {
            throw new RuntimeException("username doesn't exist");
        }

        User user = userRepository.findByUsername(username).get();
        if (!encodedPassword.equals(user.getPassword())) throw new RuntimeException("password doesn't match");

        sessionManager.createSession(user, response);
    }
}
