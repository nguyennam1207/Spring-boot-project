package com.example.spring_boot_project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping("/users")
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
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable int id) {
        us.deleteUser(id);
    }

}
