package com.example.demo.service;

import com.example.demo.dto.request.UserDto;
import com.example.demo.entity.User;
import com.example.demo.exception.type.InvalidInputException;
import com.example.demo.exception.type.InvalidPasswordException;
import com.example.demo.exception.type.UsernameAlreadyExistsException;
import com.example.demo.exception.type.UsernameNotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.SessionManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private SessionManager sessionManager;

    @InjectMocks
    private UserService userService;

    private UserDto userDto;

    private User user;

    private String username;

    private String password;

    private String encodedPassword;

    private final HttpServletResponse response = new MockHttpServletResponse();

    @BeforeEach
    void init() {
        user = User.builder()
                .username("haley")
                .password("9af15b336e6a9619928537df30b2e6a2376569fcf9d7e773eccede65606529a0")
                .build();
        userDto = new UserDto("haley", "0000");
        username = "haley";
        password = "0000";

    }

    @Test
    @DisplayName("유저 만들기에 성공한다.")
    void create_user_success() throws Exception {

        //given
        when(userRepository.findByUsername("haley")).thenReturn(Optional.empty());

        //when
       userService.create(userDto);

       //then
        verify(userRepository).findByUsername("haley");
        verify(userRepository).save(any(User.class));

    }

    @Test
    @DisplayName("유저 아이디가 이미 존재하여 유저 만들기에 실패한다.")
    void create_user_failure_existing_username() throws  Exception {
        //given
        when(userRepository.findByUsername("haley")).thenReturn(Optional.of(user));

        //when & then
        Assertions.assertThrows(UsernameAlreadyExistsException.class, () -> userService.create(userDto));

    }

    @Test
    @DisplayName("유저 아이디에 숫자, 영어 외 다른 기호가 포함되어 유저 만들기에 실패한다.")
    void create_user_failure_with_invalid_username_character() throws Exception {
        //given
        String username = "haley&";
        UserDto userDto = new UserDto(username, password);

        //when & then
        Assertions.assertThrows(InvalidInputException.class, () -> userService.create(userDto));
    }

    @Test
    @DisplayName("유저 비밀번호에 숫자, 영어 외 다른 기호가 포함되어 유저 만들기에 실패한다.")
    void create_user_failure_with_invalid_password_character() throws Exception {
        //given
        String password = "00aa!";
        UserDto userDto1 = new UserDto(username, password);

        //when & then
        Assertions.assertThrows(InvalidInputException.class, () -> userService.create(userDto1));
    }

    @Test
    @DisplayName("아이디를 입력하지 않아 유저 만들기에 실패한다.")
    void create_user_failure_with_no_username() throws Exception {
        //given
        String username = "";
        UserDto userDto1 = new UserDto(username, password);

        //when & then
        Assertions.assertThrows(InvalidInputException.class, () -> userService.create(userDto1));
    }

    @Test
    @DisplayName("비밀번호를 입력하지 않아 유저 만들기에 실패한다.")
    void create_user_failure_with_no_password() throws Exception {
        //given
        String password = "";
        UserDto userDto1 = new UserDto(username, password);

        //when & then
        Assertions.assertThrows(InvalidInputException.class, () -> userService.create(userDto1));
    }

    @Test
    @DisplayName("로그인에 성공한다.")
    void login_success() {
        //given
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        //when
        userService.login(userDto, response);

        //then
        verify(userRepository).findByUsername(username);
        verify(sessionManager).createSession(user, response);
    }

    @Test
    @DisplayName("유저 아이디를 찾지 못해 로그인에 실패한다.")
    void login_failure_with_invalid_username() {
        //given
        when(userRepository.findByUsername(username)).thenReturn(Optional.empty());

        //when & then
        Assertions.assertThrows(UsernameNotFoundException.class, ()->userService.login(userDto, response));
    }

    @Test
    @DisplayName("비밀번호가 맞지 않아 로그인에 실패한다.")
    void login_failure_with_invalid_password() {
        //given
        String password = "1234";
        UserDto userDto1 = new UserDto(username, password);
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        //when & then
        Assertions.assertThrows(InvalidPasswordException.class, ()->userService.login(userDto1, response));
    }
}

//    @Test
//    @DisplayName("유저 아이디가 길어서 유저 만들기에 실패한다.")
//    void create_user_failure_with_long_username() throws Exception {
//        //given
//        String username = "haleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaleyhaley";
//        String password = "0000";
//
//        //when & then
//        Assertions.assertThrows(MethodArgumentNotValidException.class, UserDto::new);
//    }

//    @Test
//    @DisplayName("유저 비밀번호가 길어서 유저 만들기에 실패한다.")
//    void create_user_failure_with_long_password() throws Exception {
//        //given
//       String username = "haley";
//        String password = "000012340000123400001234000012340000123400001234000012340000123400001234000012340000123400001234000012340000123400001234000012340000123400001234000012340000123400001234000012340000123400001234000012340000123400001234000012340000123400001234000012340000123400001234";
//        UserDto userDto1 = new UserDto(username, password);
//
//        //when & then
//        Assertions.assertThrows(MethodArgumentNotValidException.class, () -> userService.create(userDto1));
//    }
