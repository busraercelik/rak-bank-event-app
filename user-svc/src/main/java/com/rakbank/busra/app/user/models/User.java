package com.rakbank.busra.app.user.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity(name = "app_user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    String name;
    @Enumerated(EnumType.STRING)
    Gender gender;
    @Column(name = "email", unique = true)
    String email;
    @Column(name = "phone", unique = true)
    String phone;
}