package com.example.demo.exception.type;

import com.example.demo.exception.CustomException;
import com.example.demo.exception.ErrorCode;

public class NoFormulaByThisUsernameException extends CustomException {
    private static final long serialVersionUID = 1L;
    public NoFormulaByThisUsernameException(ErrorCode errorCode) {super(errorCode);}
    public NoFormulaByThisUsernameException(String message, ErrorCode errorCode) {
        super(message, errorCode);
    }
    public NoFormulaByThisUsernameException() {
        super(ErrorCode.NO_FORMULA_BY_THIS_USERNAME);
    }
}
