package com.example.spring_boot_demo.service;

import com.example.spring_boot_demo.payload.JwtAuthenticationRequest;
import com.example.spring_boot_demo.payload.RegisterDto;

public interface AuthenticationService {

    String login(JwtAuthenticationRequest jwtAuthReq);

    String register(RegisterDto registerDto);

}
