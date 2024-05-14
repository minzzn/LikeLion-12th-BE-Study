package org.example.springstudy.Repository;

import org.example.springstudy.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByNameAndAddress(String name, String address);
    User findByEmail(String email);
}
