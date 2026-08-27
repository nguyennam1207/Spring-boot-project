package com.example.spring_boot_project.service;

<<<<<<< HEAD
import java.util.ArrayList;
=======
import java.security.Security;
import java.util.ArrayList;
import java.util.HashSet;
>>>>>>> 6aed02d (final update)
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< HEAD
=======
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
>>>>>>> 6aed02d (final update)
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring_boot_project.dto.request.UpdateUserRequest;
import com.example.spring_boot_project.dto.request.UsersRequest;
import com.example.spring_boot_project.dto.response.UsersResponse;
import com.example.spring_boot_project.entity.Users;
<<<<<<< HEAD
=======
import com.example.spring_boot_project.enums.Role;
>>>>>>> 6aed02d (final update)
import com.example.spring_boot_project.exception.AppException;
import com.example.spring_boot_project.exception.ErrorCode;
import com.example.spring_boot_project.mapper.UserMapper;
import com.example.spring_boot_project.repository.UsersRepository;

<<<<<<< HEAD
@Service
=======
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
>>>>>>> 6aed02d (final update)
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

<<<<<<< HEAD
        return userRepository.save(us);
    }

    public List<UsersResponse> getallUser() {
=======
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        us.setRole(roles);

        return userRepository.save(us);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UsersResponse> getallUser() {
        log.info("Dang truy cap vao method getallUser");

>>>>>>> 6aed02d (final update)
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

<<<<<<< HEAD
=======
    @PostAuthorize("returnObject.username = authentication.name")
    public UsersResponse findByUserName() {

        var context = SecurityContextHolder.getContext().getAuthentication();

        String userName = context.getName();

        return userMapper.toUserResponse(userRepository.findByUserName(userName)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND))
        );
    }

>>>>>>> 6aed02d (final update)
    public UsersResponse findByID(int id) {
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND))
        );
    }

}
