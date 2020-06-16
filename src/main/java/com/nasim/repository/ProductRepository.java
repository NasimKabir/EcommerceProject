package com.nasim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nasim.model.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findAllByGender(String gender);
}
