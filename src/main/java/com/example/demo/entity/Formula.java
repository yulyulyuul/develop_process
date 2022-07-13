package com.example.demo.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(
        name = "FORMULA_SEQ_GENERATOR",
        sequenceName = "FORMULA_SEQ",
        allocationSize = 1
)
public class Formula {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORMULA_SEQ_GENERATOR")
    @Column(name = "FORMULA_ID")
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String formula;

    @NotNull
    private Boolean isCorrect = true;

    @Builder
    public Formula(String username, String formula, Boolean isCorrect) {
        Assert.notNull(username, "username must not be null");
        Assert.notNull(formula, "formula must not be null");
        this.username = username;
        this.formula = formula;
        this.isCorrect = isCorrect;
    }

}
