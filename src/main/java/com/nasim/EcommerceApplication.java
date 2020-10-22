package com.nasim;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nasim.model.ERole;
import com.nasim.model.Role;
import com.nasim.repository.RoleRepository;

@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
		 * Role role1 = new Role(); role1.setName(ERole.ROLE_ADMIN); Role role = new
		 * Role(); role.setName(ERole.ROLE_USER); logger.info("Employee information {}",
		 * roleRepository.save(role)); logger.info("Employee information {}",
		 * roleRepository.save(role1));
		 */
	}

}
