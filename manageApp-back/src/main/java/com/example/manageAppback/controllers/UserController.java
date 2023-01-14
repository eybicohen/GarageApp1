package com.example.manageAppback.controllers;

import com.example.manageAppback.models.User;
import com.example.manageAppback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getAll() {
        return this.userService.getAll();
    }

    @GetMapping("/{email}")
    public User getByEmail(@PathVariable String email) {
        return this.userService.getByEmail(email);
    }

    @PostMapping("")
    public void addUser(@RequestBody User newUser) {
        this.userService.addUser(newUser);
    }


    @GetMapping("/is-only-google-user")
    public Boolean isOnlyGoogleUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        User user = this.userService.getUserByToken(token);
        return user.getPassword() == null;
    }

    @PatchMapping("/change-password")
    public void changePassword(@RequestBody String password, @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        this.userService.changePassword(token, password);
    }
}
