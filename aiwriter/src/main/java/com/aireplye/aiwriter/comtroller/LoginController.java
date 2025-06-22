package com.aireplye.aiwriter.comtroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aireplye.aiwriter.config.JwtHelper;
import com.aireplye.aiwriter.dto.LoginDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class LoginController {

    @Autowired 
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
            );
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtHelper.generateToken(userDetails);
            return ResponseEntity.ok(Map.of("token",token));
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error","Invalid Credentials"), HttpStatus.UNAUTHORIZED);
        }   
    }
}
