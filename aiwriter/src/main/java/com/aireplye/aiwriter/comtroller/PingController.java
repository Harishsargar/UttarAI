package com.aireplye.aiwriter.comtroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ping")
@CrossOrigin(origins = "*")
public class PingController {

    @GetMapping("/isbackendup")
    public ResponseEntity<String> isBackendUp(){
        System.out.println("ping Backend called.........");
        return new ResponseEntity<>("Uttar-AI Backend is running",HttpStatus.OK);
    }
}
