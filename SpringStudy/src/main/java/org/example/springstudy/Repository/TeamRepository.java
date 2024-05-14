package org.example.springstudy.Repository;

import org.example.springstudy.Entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
