package com.aireplye.aiwriter.comtroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aireplye.aiwriter.dto.RegisterDTO;
import com.aireplye.aiwriter.service.UserService;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class RegisterController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO){
        System.out.println("register called");
        if(!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())){
            return new ResponseEntity<>(Map.of("error","password and confirm password does not match"), HttpStatus.BAD_REQUEST);
        }
        userService.registerUser(registerDTO);
        return new ResponseEntity<>(Map.of("message","user register successfully"), HttpStatus.CREATED);
    }
}
