package com.example.train.repository;

import com.example.train.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository                         // table manipulation
public interface UserRepository extends JpaRepository<User, Long> {          // primary key data type is long
	Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
