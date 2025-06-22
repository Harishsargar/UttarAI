package com.aireplye.aiwriter.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String name;
    private String email;
    private String phoneNumber;
    private String gender;
    private String password;
    private String confirmPassword;
}
