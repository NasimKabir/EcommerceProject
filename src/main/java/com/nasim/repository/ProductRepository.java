package com.nasim.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.nasim.model.Product;


public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}
