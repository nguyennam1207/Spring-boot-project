package com.example.spring_boot_project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.spring_boot_project.dto.request.AuthenticationRequest;
import com.example.spring_boot_project.dto.request.UpdateUserRequest;
import com.example.spring_boot_project.dto.request.UsersRequest;
import com.example.spring_boot_project.dto.response.UsersResponse;
import com.example.spring_boot_project.entity.Users;

@Mapper(componentModel = "spring")
public interface UserMapper {

    Users toUser(UsersRequest usersRequest);

    @Mapping(target = "role", ignore = true)
    UsersResponse toUserResponse(Users user);

    void updateUser(@MappingTarget Users user, UpdateUserRequest updateUsersRequest);

}
