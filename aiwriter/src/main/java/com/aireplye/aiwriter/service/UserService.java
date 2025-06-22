package com.aireplye.aiwriter.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aireplye.aiwriter.dto.RegisterDTO;
import com.aireplye.aiwriter.entity.User;
import com.aireplye.aiwriter.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    // register user
    public User registerUser(RegisterDTO registerDTO) {
        if(userRepo.existsByEmail(registerDTO.getEmail())){
            throw new IllegalArgumentException("email Already register");
        }
        User user = new User();
        user.setId(UUID.randomUUID().toString()); // generate unique id
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword())); // encode password before storing in db
        user.setEmail(registerDTO.getEmail());
        user.setGender(registerDTO.getGender());
        user.setName(registerDTO.getName());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        return userRepo.save(user);
    }
}
