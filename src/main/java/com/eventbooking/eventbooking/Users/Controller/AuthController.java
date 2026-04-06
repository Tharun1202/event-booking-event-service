package com.eventbooking.eventbooking.Users.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventbooking.eventbooking.Users.Dto.Request.CreateAdminUser;
import com.eventbooking.eventbooking.Users.Dto.Request.CreateUser;
import com.eventbooking.eventbooking.Users.Dto.Request.LoginRequest;
import com.eventbooking.eventbooking.Users.Dto.Response.LoginResponse;
import com.eventbooking.eventbooking.Users.Dto.Response.UserCreationResponse;
import com.eventbooking.eventbooking.Users.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping
public class AuthController {
    @Autowired
    UserService service;

    @PostMapping("/user/register")
    public UserCreationResponse registerNewUsers(@Valid @RequestBody CreateUser request)
    {
        return service.createUsers(request);
    }

    @PostMapping("/auth/login")
    public LoginResponse ValidateLoginRequest(@Valid @RequestBody LoginRequest request)
    {
        return service.authenticateLoginRequest(request);
    }

    @PostMapping("/admin/register")
    public UserCreationResponse registerNewAdmins(@Valid @RequestBody CreateAdminUser request)
    {
        return service.createAdmin(request);
    }
}
