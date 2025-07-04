package com.aireplye.aiwriter.repository;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aireplye.aiwriter.entity.User;


@Repository
@Profile("mysql")
public interface UserRepo extends JpaRepository<User, String> { 
    Optional<User> findByEmail(String email);

    //check if email exist 
    boolean existsByEmail(String email);
}

