package com.weCode.bookStore.controller;

import com.weCode.bookStore.config.JwtUtil;
import com.weCode.bookStore.dto.AuthenticationRequest;
import com.weCode.bookStore.dto.AuthenticationResponse;
import com.weCode.bookStore.service.UserDetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailService userDetailService;
    private final JwtUtil jwtUtil;

    public UserController(AuthenticationManager authenticationManager, UserDetailService userDetailService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new RuntimeException("username or password incorrect");
        }

        UserDetails userDetails = userDetailService.loadUserByUsername(request.getEmail());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse("Bearer " + token));
    }
}
