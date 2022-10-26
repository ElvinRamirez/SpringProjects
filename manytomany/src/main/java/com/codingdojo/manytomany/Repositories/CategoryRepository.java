package com.codingdojo.manytomany.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.manytomany.models.Category;

public interface CategoryRepository extends CrudRepository <Category, Long>{
	List<Category> findAll();
}
