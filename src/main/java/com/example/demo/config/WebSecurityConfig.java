package com.example.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import java.util.Arrays;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/formula/save", "formula/getAll").permitAll()
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .headers()
                .frameOptions().sameOrigin()
                .and()
                .formLogin()
                .disable();
    }
}
