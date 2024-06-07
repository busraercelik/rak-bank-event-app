package com.rakbank.busra.app.eventmgmt.clients.userservice.dtos.commons;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO {
    String name;
    Gender gender;
    String email;
    String phone;

    enum Gender {
        MALE, FEMALE, OTHER
    }
}
