package com.codedifferently.CD_InternTracker.repos;

import com.codedifferently.CD_InternTracker.models.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternRepository extends JpaRepository<Intern, Long> {
}
