package com.example.demo.exception.type;

import com.example.demo.exception.CustomException;
import com.example.demo.exception.ErrorCode;

public class UsernameAlreadyExistsException extends CustomException {

    private static final long serialVersionUID = 1L;

    public UsernameAlreadyExistsException(ErrorCode errorCode) {super(errorCode);}

    public UsernameAlreadyExistsException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }

    public UsernameAlreadyExistsException() {
        super(ErrorCode.USERNAME_ALREADY_EXISTS);
    }
}
