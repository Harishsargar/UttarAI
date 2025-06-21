package com.aireplye.aiwriter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private String gender;
    private String password;
}
