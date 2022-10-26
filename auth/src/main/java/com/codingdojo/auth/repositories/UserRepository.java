package com.codingdojo.auth.repositories;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.auth.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
