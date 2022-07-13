package com.example.demo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FormulaDto {

    @NotBlank
    @Length(max = 255, message = "아이디가 너무 김")
    private String username;

    @NotBlank
    @Length(max = 255, message = "수식이 너무 김")
    private String formula;
}
