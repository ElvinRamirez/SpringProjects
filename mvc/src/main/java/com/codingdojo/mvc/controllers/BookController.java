package com.codingdojo.mvc.controllers;


import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.mvc.models.Book;
import com.codingdojo.mvc.services.BookService;

@Controller
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@GetMapping("/books/{bookId}")
	public String book(Model model, @PathVariable("bookId")Long bookId){
		Book book = bookService.findBook(bookId);
		
		ArrayList<Book> books = (ArrayList<Book>) bookService.allBooks();
		
		model.addAttribute("book", book);
		model.addAttribute("books", books);
		return "show.jsp";
	}
	
	@GetMapping("/books")
	public String index(Model model) {
		ArrayList<Book> books = (ArrayList<Book>) bookService.allBooks();
		model.addAttribute("books", books);
		return "index.jsp";
	}
	
	@PostMapping("/new")
	public String newBook(@Valid @ModelAttribute("book")Book book,
			BindingResult result) {
		if (result.hasErrors()) {
			return "new.jsp";
		}
		bookService.createBook(book);
		
		return "redirect:/books";
	}
	
	@GetMapping("/form")
	public String addBook(@ModelAttribute("book")Book book) {
		return "new.jsp";
	}
	
	@GetMapping("/books/{id}/edit")
	public String edit(@PathVariable("id")Long id, Model model) {
		Book book = bookService.findBook(id);
		model.addAttribute("book", book);
		return "edit.jsp";
	}
	
	@PutMapping("books/{id}")
	public String update(@Valid @ModelAttribute("book")Book book, BindingResult result) {
		if(result.hasErrors()) {
			return "edit.jsp";
		}else {
			bookService.updateBook(book);
			return "redirect:/books";
		}
	}
	
	@DeleteMapping("/books/{id}")
	public String destroy(@PathVariable("id")Long id) {
		bookService.deleteBook(id);
		return "redirect:/books";
	}
}
