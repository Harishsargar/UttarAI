package com.aireplye.aiwriter.mongoEntity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Document(collection = "users")
public class User implements UserDetails {
    @Id
    private String id;
    private String name;
    @Column(unique = true)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    private String email;
    private String phoneNumber;
    private String gender;
    private String password;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // theirs no role in this project 
        return Collections.emptyList();
    }
    @Override
    public String getUsername() {
        return this.email;
    }
}
