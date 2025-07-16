package com.aireplye.aiwriter.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aireplye.aiwriter.dto.RegisterDTO;
// import com.aireplye.aiwriter.entity.User;
// import com.aireplye.aiwriter.repository.UserRepo;
import com.aireplye.aiwriter.mongoEntity.User;
import com.aireplye.aiwriter.mongoRepo.UserRepo;

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
        user.setCurrentPlan("Free_Tier");
        user.setApiCalls(25);    // this are the no of calls we are giving for frees
        return userRepo.save(user);
    }


    public User getUserByUsername(String username){
        User user = userRepo.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("user not found :"+username));
        return user;
    }

    public User saveUser(User user){
        return userRepo.save(user);
    }
}
