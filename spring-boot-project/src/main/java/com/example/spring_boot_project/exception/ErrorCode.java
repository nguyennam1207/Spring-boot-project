package com.example.spring_boot_project.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    USER_EXIST(1002, "Ten dang nhap da ton tai", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1003, "Mat khau khong hop le", HttpStatus.BAD_REQUEST),
    INVALID_ENUM_KEY(1001, "Gia tri enum khong hop le", HttpStatus.BAD_REQUEST),
    UNCATHEGORIZED_EXCEPTION(9999, "Loi chua xac dinh", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_FOUND(1004, "Khong tim thay nguoi dung", HttpStatus.NOT_FOUND),
    UNAUTHORIZED(1005, "Khong co quyen truy cap", HttpStatus.UNAUTHORIZED);

    private int code;
    private String message;
    private HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

}
