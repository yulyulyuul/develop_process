package com.example.demo.repository;

import com.example.demo.entity.Formula;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface FormulaRepository  extends JpaRepository<Formula, Long> {
    List<Formula> findAllByUsername(String username, Pageable pageable);
}
