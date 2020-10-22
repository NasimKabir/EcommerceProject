package com.nasim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nasim.model.ERole;
import com.nasim.model.Role;



public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByName(ERole name);
}
