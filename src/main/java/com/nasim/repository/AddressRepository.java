package com.nasim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nasim.model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
