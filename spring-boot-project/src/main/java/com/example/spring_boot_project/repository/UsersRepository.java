package com.example.spring_boot_project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.spring_boot_project.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUserName(String userName);
    // @Query("SELECT u FROM Users u WHERE u.userName = :userName")
    // public Users findByUserName(String userName);

    public Users getById(int id);

}
