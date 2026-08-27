package com.example.spring_boot_project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_boot_project.dto.request.ApiResponse;
import com.example.spring_boot_project.dto.request.UpdateUserRequest;
import com.example.spring_boot_project.dto.request.UsersRequest;
import com.example.spring_boot_project.dto.response.UsersResponse;
import com.example.spring_boot_project.entity.Users;
import com.example.spring_boot_project.exception.AppException;
import com.example.spring_boot_project.mapper.UserMapper;
import com.example.spring_boot_project.service.UsersService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class UserController {

    @Autowired
    private UsersService us;

    @PostMapping
    ApiResponse<UsersResponse> createUsers(@RequestBody @Valid UsersRequest request) {
        return ApiResponse.<UsersResponse>builder()
                .code(200)
                .result(us.createNewUser(request))
                .build();
    }

    @GetMapping()
    ApiResponse<List<UsersResponse>> getAllUser() {

        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info(authentication.toString());

        log.info("Thong tin ten dang nhap cua nguoi dung la: {}", authentication.getName());

        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        ApiResponse<List<UsersResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(us.getallUser());
        return apiResponse;
    }

    @PutMapping("/{id}")
    ApiResponse<UsersResponse> updateUser(@PathVariable int id, @RequestBody UpdateUserRequest request) {
        ApiResponse<UsersResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(us.updateUsers(id, request));
        return apiResponse;
    }

    @GetMapping("/my_info")
    ApiResponse<UsersResponse> getMyInfo() {
        ApiResponse<UsersResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(us.findByUserName());
        return apiResponse;
    }

    @GetMapping("/{id}")
    ApiResponse<UsersResponse> findByID(@PathVariable int id) {

        ApiResponse<UsersResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(us.findByID(id));

        return apiResponse;
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable int id) {
        us.deleteUser(id);
    }

}
