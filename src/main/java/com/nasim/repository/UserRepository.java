package com.nasim.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nasim.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	Optional<User> findUserByUsername(String username);
    Boolean existsByUsername(String username);
}
