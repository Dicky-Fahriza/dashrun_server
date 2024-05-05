package com.dashrun.service;

import com.dashrun.model.User;
import com.dashrun.payload.RegisterUserRequest;
import com.dashrun.payload.UserResponse;
import com.dashrun.repository.UserRepository;
import com.dashrun.security.BCrypt;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Validator validator;

    // @Autowired
    // private Validator validator;

    public UserResponse register(RegisterUserRequest request) {
        Set<ConstraintViolation<RegisterUserRequest>> constraintViolations = validator.validate(request);

        // if (request.getRole() != "ibutam") {
        //     throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Request not allowed");
        // }
        if (!request.getRole().equals("ibutam")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Request not allowed");
        }
        

        // validasi user
        if (constraintViolations.size() > 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid username, name, or password");
        }

        // cek user is already register
        if (userRepository.existsById(request.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already registered");
        }

        // Create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setRole(request.getRole());
        user.setName(request.getName());
        user.setPassword(BCrypt.hashpw(request.getPassword(),BCrypt.gensalt()));

        userRepository.save(user);

        UserResponse response = new UserResponse(user.getUsername(), user.getName(), user.getRole());
        
        
        return response;
    }
}

