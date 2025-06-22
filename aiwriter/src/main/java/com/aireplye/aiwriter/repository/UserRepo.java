package com.aireplye.aiwriter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aireplye.aiwriter.entity.User;

public interface UserRepo extends JpaRepository<User, String> { 
    Optional<User> findByEmail(String email);

    //check if email exist 
    boolean existsByEmail(String email);
}

