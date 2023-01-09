package com.example.manageAppback.dao;

import com.example.manageAppback.models.User;
import com.example.manageAppback.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private UserService userService;

    public UserDetails findUserByEmail(String email) throws UsernameNotFoundException {
        User user = this.userService.getByEmail(email);
        String password = user.getPassword() == null ? "google user" : user.getPassword();
        return new org.springframework.security.core.userdetails.User(user.getEmail(), password,
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public Boolean isUserExist(String email) {
        User user = this.userService.getByEmail(email);
        return user != null;
    }
}
