package com.example.spring_boot_project.exception;

<<<<<<< HEAD
public enum ErrorCode {
    USER_EXIST(1002, "Ten dang nhap da ton tai"),
    INVALID_PASSWORD(1003, "Mat khau khong hop le"),
    INVALID_ENUM_KEY(1001, "Gia tri enum khong hop le"),
    UNCATHEGORIZED_EXCEPTION(9999, "Loi chua xac dinh"),
    USER_NOT_FOUND(1004, "Khong tim thay nguoi dung"),
    UNAUTHORIZED(1005, "Khong co quyen truy cap");

    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
=======
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
>>>>>>> 6aed02d (final update)
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

<<<<<<< HEAD
=======
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

>>>>>>> 6aed02d (final update)
}
