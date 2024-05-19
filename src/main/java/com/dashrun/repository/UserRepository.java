package com.dashrun.repository;

import com.dashrun.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findFirstByToken(String token);
    Optional<User> findFirstByRole(String token);
    Optional<User> findFirstByUsername(String token);

    boolean existsByUsername(String username);
}

