package com.example.spring_boot_demo.service;

import com.example.spring_boot_demo.payload.JwtAuthenticationRequest;
import com.example.spring_boot_demo.payload.JwtAuthenticationResponse;
import com.example.spring_boot_demo.payload.RegisterDto;

public interface AuthenticationService {

    JwtAuthenticationResponse login(JwtAuthenticationRequest jwtAuthReq);

    void register(RegisterDto registerDto);

}
