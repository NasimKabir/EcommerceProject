package com.nasim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nasim.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findUserByUsername(String username);
    Boolean existsByUsername(String username);
}
