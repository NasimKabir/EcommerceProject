package com.nasim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nasim.model.Category;


public interface CategoryRepository extends JpaRepository<Category, Long> {
	Optional<Category> findByName(String categoryName);
	Boolean existsByName(String categoryName);
}
