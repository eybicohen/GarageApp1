package com.example.manageAppback.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = null;
    }

    public User() {
    }

    @Id
    @Column(name = "id")
    @JsonProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    @JsonProperty
    private String firstName;

    @Column(name = "last_name")
    @JsonProperty
    private String lastName;

    @Column(name = "email")
    @JsonProperty
    private String email;


    @Column(name = "password")
    @JsonProperty
    private String password;

    public Integer getId() {
        return this.id;
    }


    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }
}
