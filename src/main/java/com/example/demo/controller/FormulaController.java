package com.example.demo.controller;

import com.example.demo.dto.request.FormulaDto;
import com.example.demo.dto.request.UsernameDto;
import com.example.demo.dto.response.AllFormulaResponseDto;
import com.example.demo.dto.response.FormulaResponseDto;
import com.example.demo.service.FormulaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/formula")
@Slf4j
public class FormulaController {

    private final FormulaService formulaService;

    @PostMapping("/save")
    public ResponseEntity<FormulaResponseDto> save (@RequestBody FormulaDto formulaDto) {
        FormulaResponseDto response = formulaService.save(formulaDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AllFormulaResponseDto>> getAll (@RequestBody UsernameDto usernameDto) {
        List<AllFormulaResponseDto> response = formulaService.getAll(usernameDto.getUsername());
        return ResponseEntity.ok(response);
    }
}
