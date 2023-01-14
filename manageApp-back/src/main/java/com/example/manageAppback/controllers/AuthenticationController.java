package com.example.manageAppback.controllers;

import com.example.manageAppback.config.JwtUtils;
import com.example.manageAppback.dao.UserDao;
import com.example.manageAppback.dto.AuthenticationRequest;
import com.example.manageAppback.dto.GoogleUser;
import com.example.manageAppback.models.User;
import com.example.manageAppback.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@CrossOrigin
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private final UserDao userDao;
    @Autowired
    private final UserService userService;
    private final JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest request) {
        final Boolean isOk = userService.isPasswordCorrect(request.getPassword(), request.getEmail());
        if (!isOk) {
            return ResponseEntity.status(400).body("some error occurred");
        }
        final UserDetails user = userDao.findUserByEmail(request.getEmail());
        return ResponseEntity.ok(jwtUtils.generateToken(user));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody User newUser) {
        UserDetails user = new org.springframework.security.core.userdetails.User(newUser.getEmail(), newUser.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        this.userService.addUser(newUser);
        return ResponseEntity.ok(jwtUtils.generateToken(user));
    }

    @PostMapping("/login-google")
    public ResponseEntity<String> login(@RequestBody GoogleUser googleUser) {
        if (userDao.isUserExist(googleUser.getEmail())) {
            return ResponseEntity.ok(jwtUtils.generateToken(new org.springframework.security.core.userdetails.User(googleUser.getEmail(), "google user",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))));
        }
        this.userService.addUser(new User(googleUser.getFirstName(), googleUser.getLastName(), googleUser.getEmail()));
        return ResponseEntity.ok(jwtUtils.generateToken(new org.springframework.security.core.userdetails.User(googleUser.getEmail(), "google user",
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))));

    }
}
