package com.codingdojo.bookclub.controllers;

import javax.servlet.http.HttpSession;
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

import com.codingdojo.bookclub.models.Book;
import com.codingdojo.bookclub.models.LoginUser;
import com.codingdojo.bookclub.models.User;
import com.codingdojo.bookclub.services.BookService;
import com.codingdojo.bookclub.services.UserService;



@Controller
public class MainController {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
    private BookService books;

	@GetMapping("/")
    public String index(Model model) {
    
        // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
	
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        
        // TO-DO Later -- call a register method in the service 
        // to do some extra validations and create a new user!
        User u = userServ.register(newUser, result);
        if(result.hasErrors()) {
        		//for model.addAttribute, left the key/left side goes in the jsp form, and hte right is the piece of info from the model
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        
        //If No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        session.setAttribute("loggedIn",u.getId());
    
        return "redirect:/books";
    }
    

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
        // Add once service is implemented:
        // User user = userServ.login(newLogin, result);
    	User loggingIn = userServ.login(newLogin, result);
    	
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        //Session name should be the same across the board
        session.setAttribute("loggedIn", loggingIn.getId());
   
    
        return "redirect:/books";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("loggedIn");
    	return "redirect:/";
    }
    
    //create mapping for displaying, adding books, editing, deleting
    
    //display books for user
    @GetMapping("/books")
    public String books(Model model,HttpSession session) {
    	if (session.getAttribute("loggedIn") == null) {
    		return "redirect:/";
    	}
    	//always type cast session to a Long
    	model.addAttribute("books",books.all());
    	model.addAttribute("user", userServ.find((Long)session.getAttribute("loggedIn")));
    	return "books.jsp";
    }
    
    //form to create book
    @GetMapping("/createForm")
    public String createBook(@ModelAttribute("book") Book book, Model model, HttpSession session) {
    	
    	User user = userServ.find((Long)session.getAttribute("loggedIn"));
    	model.addAttribute("user", user);
    	
    	return "createBook.jsp";
    }
    //handle form 
    @PostMapping("/createBook")
    public String createBook(@Valid @ModelAttribute("book") Book book, BindingResult result) {

    	if (result.hasErrors()) {
    		return "createBook.jsp";
    	}
    	
    	books.create(book);
    	
    	return "redirect:/books";
    }
    
    //show specific book
    @GetMapping("/books/{id}")
    public String showPage(Model model, @PathVariable("id") Long id, HttpSession session) {
    	if(session.getAttribute("loggedIn") == null) {
    		return "redirect:/";
    	}
    	Book book = books.findById(id);
    	model.addAttribute("book", book);
    	model.addAttribute("user", userServ.find((Long)session.getAttribute("loggedIn")));
    	
    	return "oneBook.jsp";
    }
    
    //show edit page
    @GetMapping("/books/{id}/edit")
    public String editPage(Model model, @PathVariable("id") Long id, HttpSession session) {
    	
    	if(session.getAttribute("loggedIn") == null) {
    		return "redirect:/";
    	}
    	
    	Book book = books.findById(id);
    	model.addAttribute("book", book);
    	
    	return "edit.jsp";
    }
    
    //handle edit
    @PutMapping("/books/{id}")
    public String editBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
    	
    	if (result.hasErrors()) {
    		return "edit.jsp";
    	}

        books.update(book);
            	
    	return "redirect:/books";
    }
    
    //delete book
	@DeleteMapping("/books/{id}/delete")
	public String delete(@PathVariable Long id) {
		books.delete(id);
	return "redirect:/books";
	}
    
}

