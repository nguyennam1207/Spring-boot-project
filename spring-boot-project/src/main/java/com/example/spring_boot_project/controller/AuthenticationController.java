package com.example.spring_boot_project.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_project.dto.request.ApiResponse;
import com.example.spring_boot_project.dto.request.AuthenticationRequest;
import com.example.spring_boot_project.dto.request.IntrospectRequest;
import com.example.spring_boot_project.dto.request.UsersRequest;
import com.example.spring_boot_project.dto.response.AuthenticationResponse;
import com.example.spring_boot_project.dto.response.IntrospectResponse;
import com.example.spring_boot_project.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse result = authenticationService.authenticate(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request2) throws JOSEException, ParseException {
        IntrospectResponse result = authenticationService.introspect(request2);

        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

}
