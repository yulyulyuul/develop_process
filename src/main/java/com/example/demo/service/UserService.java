package com.example.demo.service;

import com.example.demo.dto.request.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public void create(UserDto userDto) {

        String username = userDto.getUsername();
        String password = userDto.getPassword();

        if (alreadyExists(username)) {throw new RuntimeException("username already exists");}
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

    private boolean alreadyExists(String username) {
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

    public void login(UserDto userDto) {
        String username = userDto.getUsername();
        String password = userDto.getPassword();
        String encodedPassword = encodePassword(password);
        log.info(username, password);

        if (!alreadyExists(username)) {
            throw new RuntimeException("username doesn't exist");
        }

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!encodedPassword.equals(userOptional.get().getPassword())) throw new RuntimeException("password doesn't match");
    }
}
