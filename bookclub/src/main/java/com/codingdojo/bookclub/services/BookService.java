package com.codingdojo.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.bookclub.models.Book;
import com.codingdojo.bookclub.repositories.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepo;
	
	public Book findById(Long id) {
		
		Optional<Book> result = bookRepo.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		
		return null;
	}
	

	public List<Book> all() {
		return bookRepo.findAll();
	}
	
	public Book update(Book book) {
		return bookRepo.save(book);
	}
	
	public Book create(Book book) {
		return bookRepo.save(book);
	}
	
	public void delete(Long id) {
		bookRepo.deleteById(id);
	}
}
