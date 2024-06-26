package com.rakbank.busra.app.user.dtos;

import com.rakbank.busra.app.user.models.Gender;
import lombok.Data;

@Data
public class UserDTO {
    String name;
    Gender gender;
    String email;
    String phone;
}
