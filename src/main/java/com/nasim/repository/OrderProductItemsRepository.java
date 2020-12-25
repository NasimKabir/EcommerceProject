package com.nasim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nasim.model.OrderProductItems;

public interface OrderProductItemsRepository extends JpaRepository<OrderProductItems, Long> {
	@Query(value = "SELECT sum(p.productQuantity) FROM OrderProductItems p")
	Long quantity();

	List<OrderProductItems> findAllById(Long id);
}
