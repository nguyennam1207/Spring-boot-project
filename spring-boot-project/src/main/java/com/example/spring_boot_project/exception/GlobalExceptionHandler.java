package com.example.spring_boot_project.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.spring_boot_project.dto.request.ApiResponse;

<<<<<<< HEAD
=======
import lombok.extern.slf4j.Slf4j;

@Slf4j
>>>>>>> 6aed02d (final update)
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(400);
        apiResponse.setMessagge(exception.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(exception.getErrorCode().getCode());
        apiResponse.setMessagge(exception.getErrorCode().getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingException(Exception exception) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(ErrorCode.UNCATHEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessagge(ErrorCode.UNCATHEGORIZED_EXCEPTION.getMessage());

<<<<<<< HEAD
        return ResponseEntity.badRequest().body(apiResponse);
=======
        return ResponseEntity.status(ErrorCode.UNCATHEGORIZED_EXCEPTION.getHttpStatus())
                .body(apiResponse);
>>>>>>> 6aed02d (final update)
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ApiResponse apiResponse = new ApiResponse();

        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_ENUM_KEY;

        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (Exception e) {
        }

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessagge(errorCode.getMessage());

<<<<<<< HEAD
        return ResponseEntity.badRequest().body(apiResponse);
=======
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(apiResponse);
>>>>>>> 6aed02d (final update)

    }

}
