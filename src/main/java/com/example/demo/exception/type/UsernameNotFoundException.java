package com.example.demo.exception.type;

import com.example.demo.exception.CustomException;
import com.example.demo.exception.ErrorCode;

import java.util.function.Supplier;

public class UsernameNotFoundException extends CustomException {

    private static final long serialVersionUID = 1L;

    public UsernameNotFoundException(ErrorCode errorCode) {super(errorCode);}

    public UsernameNotFoundException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public UsernameNotFoundException() {
        super(ErrorCode.USERNAME_NOT_FOUND);
    }
}
