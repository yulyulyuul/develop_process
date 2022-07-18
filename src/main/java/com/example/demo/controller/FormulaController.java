package com.example.demo.controller;

import com.example.demo.dto.request.FormulaDto;
import com.example.demo.dto.request.UsernameDto;
import com.example.demo.dto.response.FormulaByUsernameResponseDto;
import com.example.demo.dto.response.FormulaResponseDto;
import com.example.demo.service.FormulaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/formula")
@Slf4j
public class FormulaController {

    private final FormulaService formulaService;

    @PostMapping("/save")
    public ResponseEntity<FormulaResponseDto> save (HttpServletRequest request, @Valid @RequestBody FormulaDto formulaDto) {
        FormulaResponseDto response = formulaService.save(request, formulaDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<FormulaByUsernameResponseDto>> getAll (HttpServletRequest request, @Valid @RequestBody UsernameDto usernameDto,
                                                                      @PageableDefault(size=5, sort="formula", direction= Sort.Direction.DESC) Pageable pageable) {
        List<FormulaByUsernameResponseDto> response = formulaService.getAll(request, usernameDto.getUsername(), pageable);
        return ResponseEntity.ok(response);
    }
}
