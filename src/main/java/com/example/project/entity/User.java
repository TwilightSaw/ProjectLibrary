package com.example.project.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @NotNull
    @Size(min = 1, max = 30, message = "Imię musi mieć pomiędzy 1 a 30 znaków")
    private String name;

    @NotNull
    @Size(min = 1, max = 50, message = "Nazwisko musi mieć pomiędzy 3 a 30 znaków")
    private String surname;

    @NotNull
    @Size(min = 3, max = 30, message = "Login music mieć pomiędzy 3 a 30 znaków")
    private String login;

    @NotNull
    private String password;

    public User() {
    }

    public User(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }
}
