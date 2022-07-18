package com.example.demo.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FormulaByUsernameResponseDto {

    @NotNull
    private String formula;

    @NotNull
    private Boolean isCorrect;

}
