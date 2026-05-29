package com.example.spring_boot_project.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    private String userName;

    @Size(min = 8, message = "INVALID_PASSWORD")
    private String passWord;

    private String firstName;

    private String lastName;

    private LocalDate dob;
}
