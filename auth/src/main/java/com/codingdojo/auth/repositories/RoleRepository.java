package com.codingdojo.auth.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.auth.models.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	List<Role> findByName(String string);
	
	List<Role> findAll();
}
