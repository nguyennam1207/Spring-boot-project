package com.example.spring_boot_project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
=======
import org.springframework.security.core.context.SecurityContextHolder;
>>>>>>> 6aed02d (final update)
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
<<<<<<< HEAD

@RestController
@RequestMapping("/users")
=======
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
>>>>>>> 6aed02d (final update)
public class UserController {

    @Autowired
    private UsersService us;

    @PostMapping
    ApiResponse<Users> createUsers(@RequestBody @Valid UsersRequest request) {
        ApiResponse<Users> apiresponse = new ApiResponse<Users>();
        apiresponse.setResult(us.createNewUser(request));
        return apiresponse;
    }

    @GetMapping()
<<<<<<< HEAD
    List<UsersResponse> getAllUser() {
        return us.getallUser();
    }

    @PutMapping("/{id}")
    UsersResponse updateUser(@PathVariable int id, @RequestBody UpdateUserRequest request) {
        return us.updateUsers(id, request);
    }

    @GetMapping("/{id}")
    UsersResponse findByID(@PathVariable int id) {
        return us.findByID(id);
=======
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
>>>>>>> 6aed02d (final update)
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable int id) {
        us.deleteUser(id);
    }

}
