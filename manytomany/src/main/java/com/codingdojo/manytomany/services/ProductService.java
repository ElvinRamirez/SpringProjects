package com.codingdojo.manytomany.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.codingdojo.manytomany.Repositories.ProductRepository;
import com.codingdojo.manytomany.models.Category;
import com.codingdojo.manytomany.models.Product;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepo;
	
	public Product findById(Long id) {
		Optional<Product> result = productRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		
		return null;
	}
}
