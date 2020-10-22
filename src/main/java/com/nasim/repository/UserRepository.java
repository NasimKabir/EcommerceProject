package com.nasim.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nasim.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
