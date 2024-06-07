package com.rakbank.busra.app.user.repositories;


import com.rakbank.busra.app.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
