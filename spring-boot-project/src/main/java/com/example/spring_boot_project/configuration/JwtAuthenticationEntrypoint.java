package com.example.spring_boot_project.configuration;

import javax.naming.AuthenticationException;

import org.springframework.http.MediaType;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.example.spring_boot_project.dto.request.ApiResponse;
import com.example.spring_boot_project.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationEntrypoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authenticationException)
            throws java.io.IOException, jakarta.servlet.ServletException {
        ErrorCode errorcode = ErrorCode.UNAUTHORIZED;

        response.setStatus(errorcode.getHttpStatus().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(errorcode.getCode())
                .messagge(errorcode.getMessage())
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
        response.flushBuffer();

    }

}
