package com.example.demo.exception.type;

import com.example.demo.exception.CustomException;
import com.example.demo.exception.ErrorCode;

public class CannotEncodePasswordException extends CustomException {

    private static final long serialVersionUID = 1L;
    public CannotEncodePasswordException(ErrorCode errorCode) {super(errorCode);}
    public CannotEncodePasswordException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
    public CannotEncodePasswordException() {
        super(ErrorCode.CANNOT_ENCODE_PASSWORD);
    }

}
