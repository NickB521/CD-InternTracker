package com.codedifferently.CD_InternTracker.repos;

import com.codedifferently.CD_InternTracker.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
