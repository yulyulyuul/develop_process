package com.example.demo.exception.type;

import com.example.demo.exception.CustomException;
import com.example.demo.exception.ErrorCode;

public class InvalidPasswordException extends CustomException {
    private static final long serialVersionUID = 1L;
    public InvalidPasswordException(ErrorCode errorCode) {super(errorCode);}
    public InvalidPasswordException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
    public InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD);
    }
}
