package com.example.spring_boot_project.service;

import java.security.Security;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring_boot_project.dto.request.UpdateUserRequest;
import com.example.spring_boot_project.dto.request.UsersRequest;
import com.example.spring_boot_project.dto.response.UsersResponse;
import com.example.spring_boot_project.entity.Users;

import com.example.spring_boot_project.enums.Role;
import com.example.spring_boot_project.exception.AppException;
import com.example.spring_boot_project.exception.ErrorCode;
import com.example.spring_boot_project.mapper.UserMapper;
import com.example.spring_boot_project.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsersService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UsersResponse createNewUser(UsersRequest usrequest) {
        if (userRepository.findByUserName(usrequest.getUserName()).isPresent()) {
            throw new AppException(ErrorCode.USER_EXIST);
        }
        Users us = userMapper.toUser(usrequest);
        PasswordEncoder ps = new BCryptPasswordEncoder(10);
        us.setPassWord(ps.encode(usrequest.getPassWord()));
        us.setRole(Role.USER.name());

        return userMapper.toUserResponse(userRepository.save(us));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UsersResponse> getallUser() {
        log.info("Dang truy cap vao method getallUser");
        List<UsersResponse> userResponses = new ArrayList<>();
        List<Users> users = userRepository.findAll();
        for (Users user : users) {
            userResponses.add(userMapper.toUserResponse(user));
        }
        return userResponses;
    }

    public UsersResponse updateUsers(int id, UpdateUserRequest updateuUserRequest) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(user, updateuUserRequest);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    @PostAuthorize("returnObject.username = authentication.name")
    public UsersResponse findByUserName() {

        var context = SecurityContextHolder.getContext().getAuthentication();

        String userName = context.getName();

        return userMapper.toUserResponse(userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND))
        );
    }

    public UsersResponse findByID(int id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND))
        );
    }

}
