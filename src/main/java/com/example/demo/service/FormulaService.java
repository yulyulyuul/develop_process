package com.example.demo.service;

import com.example.demo.dto.request.FormulaDto;
import com.example.demo.dto.response.FormulaByUsernameResponseDto;
import com.example.demo.dto.response.FormulaResponseDto;
import com.example.demo.entity.Formula;
import com.example.demo.exception.type.NoFormulaByThisUsernameException;
import com.example.demo.repository.FormulaRepository;
import com.example.demo.security.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class FormulaService {

    private final FormulaRepository formulaRepository;

    private final SessionManager sessionManager;

    public FormulaResponseDto save(HttpServletRequest request, FormulaDto formulaDto) {
        checkSession(request);
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

    private void checkSession(HttpServletRequest request) {
        Object session = sessionManager.getSession(request);
    }

    private Boolean checkIfFormulaIsRight(FormulaDto formulaDto) {
        Random random = new Random();
        Boolean isCorrect = random.nextBoolean();
        return isCorrect;
    }

    public List<FormulaByUsernameResponseDto> getAll(HttpServletRequest request, String username, Pageable pageable) {
        checkSession(request);
        List<Formula> formulas = formulaRepository.findAllByUsername(username, pageable);
        if (formulas.isEmpty()) throw new NoFormulaByThisUsernameException();
        List<FormulaByUsernameResponseDto> responseDtos = new ArrayList<>();
        for (Formula formula : formulas) {
            responseDtos.add(new FormulaByUsernameResponseDto(formula.getFormula(), formula.getIsCorrect()));
        }
        return responseDtos;
    }
}
