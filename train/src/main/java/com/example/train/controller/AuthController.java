package com.example.train.controller;
import com.example.train.model.User;
import com.example.train.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
    	try {    		
    		if(StringUtils.isBlank(user.getUsername())||StringUtils.isBlank(user.getEmail())||StringUtils.isBlank(user.getPassword())) {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fill all Mandatory fields properly");	
    		}
    		String result=userService.registerUser(user);
    		 return ResponseEntity.ok(result);
    	}catch(Exception e){
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user.");
    	}      
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
    	try {    		
    		if(StringUtils.isBlank(user.getEmail())||StringUtils.isBlank(user.getPassword())) {
    			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Fill all Mandatory fields properly");	
    		}
    		String result=userService.loginUser(user);
    		 return ResponseEntity.ok(result);
    	}catch(Exception e){
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to login user.");
    	}     
    }
}

//http://localhost:8082/api/auth/register

// http://localhost:8082/api/auth/login