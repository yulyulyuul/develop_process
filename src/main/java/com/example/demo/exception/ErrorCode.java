package com.example.demo.exception;

public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "입력값이 유효하지 않습니다."),
    USERNAME_ALREADY_EXISTS(400, "이미 존재하는 아이디입니다." ),
    CANNOT_ENCODE_PASSWORD(400, "비밀번호 인코딩에 실패했습니다."),
    USERNAME_NOT_FOUND(400, "아이디가 존재하지 않습니다."),
    INVALID_PASSWORD(400, "비밀번호가 일치하지 않습니다."),
    NO_FORMULA_BY_THIS_USERNAME(400, "해당 아이디로 입력된 수식이 존재하지 않습니다.");
    private final String message;
    private final int status;

    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}