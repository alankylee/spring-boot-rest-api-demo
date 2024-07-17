package com.example.spring_boot_demo.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring_boot_demo.entity.Role;
import com.example.spring_boot_demo.entity.User;
import com.example.spring_boot_demo.exception.BlogAPIException;
import com.example.spring_boot_demo.payload.JwtAuthenticationRequest;
import com.example.spring_boot_demo.payload.JwtAuthenticationResponse;
import com.example.spring_boot_demo.payload.RegisterDto;
import com.example.spring_boot_demo.repository.RoleRepository;
import com.example.spring_boot_demo.repository.UserRepository;
import com.example.spring_boot_demo.security.JwtTokenProvider;
import com.example.spring_boot_demo.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    public AuthenticationServiceImpl(
            AuthenticationManager authManager,
            PasswordEncoder passwordEncoder,
            JwtTokenProvider jwtTokenProvider,
            UserRepository userRepo,
            RoleRepository roleRepo) {
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Override
    public JwtAuthenticationResponse login(JwtAuthenticationRequest jwtAuthReq) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(jwtAuthReq.getEmail(), jwtAuthReq.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenProvider.generateToken(authentication);

        return new JwtAuthenticationResponse(token, "Bearer");
    }

    @Override
    public void register(RegisterDto registerDto) {
        // Check if email exists in database
        if(Boolean.TRUE.equals(userRepo.existsByEmail(registerDto.getEmail()))) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "That email is taken. Try another.");
        }

        User user = new User();
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role role = roleRepo.findByName("admin").orElse(null);
        if (role == null) {
            role = new Role();
            role.setName("admin");
            roleRepo.save(role);
        }
        roles.add(role);
        user.setRoles(roles);

        userRepo.save(user);
    }

}