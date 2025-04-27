package com.example.train.service;

import com.example.train.model.User;

import com.example.train.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
	@Autowired
    private UserRepository userRepository;
    
    public String registerUser(User user) {
    	 if(userRepository.existsByEmail(user.getEmail())) {
      	   return "Email already registered!";
         }       
        	user.setRole("USER");     
        userRepository.save(user);
        return "Registration successful!";
    }

    public String loginUser(User user) {
    	if (user.getEmail().isEmpty()) {
    		return "Email should not be empty";
    	}
    	if (user.getPassword().isEmpty()) {
    		return "Password should not be empty";
    	}
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            if(existingUser.get().getPassword().equals(user.getPassword())) {
            	return "Login successful! "+existingUser.get().getRole();
            }else {
            	return "Invalid password!";
            }
        }else {
        return "Not found!";
    }
}}
