package com.example.demo.service;

import com.example.demo.dto.request.FormulaDto;
import com.example.demo.dto.response.AllFormulaResponseDto;
import com.example.demo.dto.response.FormulaResponseDto;
import com.example.demo.entity.Formula;
import com.example.demo.repository.FormulaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class FormulaService {

    private final FormulaRepository formulaRepository;

    public FormulaResponseDto save(FormulaDto formulaDto) {
        Boolean isCorrect = checkIfFormulaIsRight(formulaDto);
        Formula formula = Formula.builder()
                        .username(formulaDto.getUsername())
                        .formula(formulaDto.getFormula())
                        .isCorrect(isCorrect)
                        .build();
        formulaRepository.save(formula);
        return FormulaResponseDto.builder()
                .isCorrect(isCorrect)
                .build();
    }

    private Boolean checkIfFormulaIsRight(FormulaDto formulaDto) {
        Random random = new Random();
        Boolean isCorrect = random.nextBoolean();
        return isCorrect;
    }

    public List<AllFormulaResponseDto> getAll(String username) {
        List<Formula> formulas = formulaRepository.findAllByUsername(username);
        if (formulas.isEmpty()) throw new RuntimeException("There is no formula by this username");
        List<AllFormulaResponseDto> responseDtos = new ArrayList<>();
        for (Formula formula : formulas) {
            responseDtos.add(createAllFormulaResponseDto(formula));
        }
        return responseDtos;
    }

    private AllFormulaResponseDto createAllFormulaResponseDto(Formula formula) {
        AllFormulaResponseDto responseDto = new AllFormulaResponseDto(formula.getFormula(), formula.getIsCorrect());
        return responseDto;
    }
}
