package com.rakbank.busra.app.user.repositories;


import com.rakbank.busra.app.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailIgnoreCaseOrPhone(@Nullable String email, @Nullable String phone);
}
