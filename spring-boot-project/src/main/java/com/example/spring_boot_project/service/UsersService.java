package com.example.spring_boot_project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring_boot_project.dto.request.UpdateUserRequest;
import com.example.spring_boot_project.dto.request.UsersRequest;
import com.example.spring_boot_project.dto.response.UsersResponse;
import com.example.spring_boot_project.entity.Users;
import com.example.spring_boot_project.exception.AppException;
import com.example.spring_boot_project.exception.ErrorCode;
import com.example.spring_boot_project.mapper.UserMapper;
import com.example.spring_boot_project.repository.UsersRepository;

@Service
public class UsersService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public Users createNewUser(UsersRequest usrequest) {
        Users us = new Users();

        if (userRepository.findByUserName(usrequest.getUserName()).isPresent()) {
            throw new AppException(ErrorCode.USER_EXIST);
        }
        us = userMapper.toUser(usrequest);
        PasswordEncoder ps = new BCryptPasswordEncoder(10);
        us.setPassWord(ps.encode(usrequest.getPassWord()));

        return userRepository.save(us);
    }

    public List<UsersResponse> getallUser() {
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

    public UsersResponse findByID(int id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND))
        );
    }

}
