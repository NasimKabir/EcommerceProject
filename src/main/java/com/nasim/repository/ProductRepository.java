package com.nasim.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.nasim.model.Category;
import com.nasim.model.Product;


public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

}
