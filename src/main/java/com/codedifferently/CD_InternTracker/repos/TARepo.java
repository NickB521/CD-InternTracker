package com.codedifferently.CD_InternTracker.repos;

import com.codedifferently.CD_InternTracker.models.TA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TARepo extends JpaRepository<TA, Long> {

    Optional<TA> findByEmail(String email);
}
