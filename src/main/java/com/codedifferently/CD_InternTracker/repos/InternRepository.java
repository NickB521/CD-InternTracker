package com.codedifferently.CD_InternTracker.repos;

import com.codedifferently.CD_InternTracker.models.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InternRepository extends JpaRepository<Intern, Long> {
    Optional<Intern> findByEmail (String email);
    List<Intern> findByLevel (String level);
}
