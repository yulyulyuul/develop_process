package com.example.demo.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ManagerAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        //원래는 session ID나 토큰 등을 통해 어떤 사람이 한건지를 받아와야 함. 근데 그냥 test용으로 이렇게 박아 놓음.
        return Optional.of("test1234");
    }
}
