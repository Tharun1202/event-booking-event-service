package com.eventbooking.eventbooking.Users.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eventbooking.eventbooking.Security.JwtService;
import com.eventbooking.eventbooking.Users.Dto.Request.CreateUser;
import com.eventbooking.eventbooking.Users.Dto.Request.LoginRequest;
import com.eventbooking.eventbooking.Users.Dto.Response.LoginResponse;
import com.eventbooking.eventbooking.Users.Dto.Response.UserCreationResponse;
import com.eventbooking.eventbooking.Users.model.User;
import com.eventbooking.eventbooking.Users.model.UserRole;
import com.eventbooking.eventbooking.Users.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    //create user , admin . validate login request and respond with a jwt token if credentials match.
    @Autowired
    UserRepository userrepo;
    @Autowired
    JwtService jwtservice;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Transactional
    public UserCreationResponse createUsers(CreateUser request)
    {
        if(userrepo.existsByUsername(request.getUsername()))
        {
            throw new IllegalArgumentException("Username already exists!!!");
        }
        if(userrepo.existsByEmail(request.getEmail()))
        {
            throw new IllegalArgumentException("E-mail already exists ... try using a different e-mail");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(UserRole.USER);
        user.setPasswordHash(encoder.encode(request.getPassword()));
        LocalDateTime time = timestamp();
        user.setCreatedAt(time);
        user.setUpdatedAt(time);
        userrepo.save(user);

        UserCreationResponse response = new UserCreationResponse();
        response.setRole(UserRole.USER);
        response.setStatus("SUCCESS");
        response.setTimestamp(time);
    return response;
    }

    public LoginResponse authenticateLoginRequest(LoginRequest request)
    {
        if(!userrepo.existsByUsername(request.getUsername()))
        {
            throw new IllegalArgumentException("Wrong username or password!!!");
        }
        User user = userrepo.findByUsername(request.getUsername()).get();
        if(!encoder.matches(request.getPassword(),user.getPasswordHash()))
        {
            throw new IllegalArgumentException("Wrong username or password!!!");
        }
        LocalDateTime time = timestamp();
        String userid = Integer.toString(user.getUserId());
        String token = jwtservice.generateJWTToken(userid,request.getUsername(),user.getRole());
        LoginResponse response =  new LoginResponse();
        response.setStatus("SCUCCESS");
        response.setTimestamp(time);
        response.setToken(token);
        return response;
    }



    public LocalDateTime timestamp()
    {
        return LocalDateTime.now();
    }
    
}
