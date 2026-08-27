package com.example.spring_boot_project.configuration;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;

import com.example.spring_boot_project.entity.Users;
import com.example.spring_boot_project.enums.Role;
import com.example.spring_boot_project.repository.UsersRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ApplicationConfigured {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UsersRepository userRepository) {
        return args -> {
            if (userRepository.findByUserName("admin").isEmpty()) {

                var roles = new HashSet<String>();
                roles.add(Role.ADMIN.name());

                Users user = Users.builder()
                        .userName("admin")
                        .passWord(passwordEncoder.encode("admin"))
                        .role(roles)
                        .build();

                userRepository.save(user);

                log.warn("Tai khoan admin da duoc khoi tao voi password la: admin");
            }
        };

    }

}
