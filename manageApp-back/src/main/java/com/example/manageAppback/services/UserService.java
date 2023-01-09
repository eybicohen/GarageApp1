package com.example.manageAppback.services;

import com.example.manageAppback.config.JwtUtils;
import com.example.manageAppback.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.manageAppback.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final JwtUtils jwtUtils;

    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    public User getByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public void addUser(User newUser) {
        this.userRepository.save(newUser);
    }


    public Boolean isPasswordCorrect(String password, String email) {
        User user = this.getByEmail(email);
        if (user != null && password != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }

    public User getUserByToken(String token) {
        String email = this.jwtUtils.extractUsername(token.substring(7));
        return this.getByEmail(email);
    }
}
