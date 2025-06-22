package com.aireplye.aiwriter.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User implements UserDetails {
    @Id
    private String id;
    private String name;
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
