package com.codingdojo.manytomany.Repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.manytomany.models.Product;

@Repository
public interface ProductRepository extends CrudRepository <Product, Long>{
	List<Product> findAll();

}
