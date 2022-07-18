package com.example.demo.exception.type;

import com.example.demo.exception.CustomException;
import com.example.demo.exception.ErrorCode;

public class InvalidInputException extends CustomException {

    private static final long serialVersionUID = 1L;
    public InvalidInputException(ErrorCode errorCode) {super(errorCode);}
    public InvalidInputException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
    public InvalidInputException() {
        super(ErrorCode.INVALID_INPUT_VALUE);
    }
}
