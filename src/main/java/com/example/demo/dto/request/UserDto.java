package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "아이디를 입력하세요")
    @Length(max = 255, message = "아이디가 너무 김")
    private String username;

    @NotBlank(message = "비밀번호를 입력하세요")
    @Length(max = 255, message = "비밀번호가 너무 김")
    private String password;

}
