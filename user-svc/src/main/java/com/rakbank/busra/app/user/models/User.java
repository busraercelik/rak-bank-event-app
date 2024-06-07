package com.rakbank.busra.app.user.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String name;
    Gender gender;
    @Column(name = "email", unique = true)
    String email;
    @Column(name = "phone", unique = true)
    String phone;
}