package com.nasim.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nasim.exception.ProductNotFoundException;
import com.nasim.model.Role;
import com.nasim.repository.RoleRepository;

@RestController
@RequestMapping("/api/v1")
public class RoleController {
	@Autowired
	RoleRepository roleRepository;

	@GetMapping("/roles")
	public ResponseEntity<List<Role>> getRolesList() {
		List<Role> role = roleRepository.findAll();
		return new ResponseEntity<List<Role>>(role, HttpStatus.OK);

	}

	@PostMapping("/roles")
	public ResponseEntity<?> createNewRole(@Valid @RequestBody Role role) {
		Role roles = roleRepository.save(role);
		roles.setIsActive(true);
		return new ResponseEntity<Role>(roles, HttpStatus.CREATED);

	}

	@GetMapping(path = "/roles/{id}")
	public ResponseEntity<?> getRoleId(@PathVariable("id") int id) {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Role id is = " + id + " not found."));
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	@DeleteMapping(path = "/roles/{id}")
	public ResponseEntity<?> deleteRole(@PathVariable("id") int id) {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Role id is = " + id + " not found."));
		return new ResponseEntity<Role>(role, HttpStatus.OK);
	}

	@PutMapping(path = "/roles/{id}")
	public ResponseEntity<?> updateRole(@PathVariable("id") int id,@Valid @RequestBody Role role) {

		Role roleUpdated = roleRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Role id is = " + id + " not found."));

		if (roleUpdated == null) {
			return null;
		}

		roleUpdated.setName(role.getName());

		roleRepository.save(roleUpdated);

		return new ResponseEntity<Role>(roleUpdated, HttpStatus.CREATED);

	}

}
