package com.codingdojo.bookclub.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.bookclub.models.LoginUser;
import com.codingdojo.bookclub.models.User;
import com.codingdojo.bookclub.repositories.UserRepository;


@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepo;
    
    public User find(Long id) {
    	return userRepo.findById(id).orElse(null);
    }
    
    // TO-DO: Write register and login methods!
    public User register(User newUser, BindingResult result) {
        // check if user's email exist in db
    	User emailCheck = userRepo.findByEmail(newUser.getEmail()).orElse(null);
    	
    	if (emailCheck != null) {
    		//rejecValue takes 3 arguments,1st one is what part of the form we are going to add the validation to,
    		//2nd need to add a key, and 3rd a message to display
    		//.trim() to remove whitespaces
    		newUser.getEmail().trim();
    		result.rejectValue("email","unique","Email nota vailable");
    	}
    	
    	//check if the passwords match
    	if (!newUser.getPassword().equals(newUser.getConfirm())) {
    		result.rejectValue("confirm", "matches", "Confirm password must match password");
    	}
        
    	if (result.hasErrors()) {
    		return null;
    	}
    	//hashing the password
    	
    	String hash = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
    	newUser.setPassword(hash);
    	
    	//save/make the user
    	return userRepo.save(newUser);
    }
    
    public User login(LoginUser logUser, BindingResult result) {
        // 
    	User userInDb = userRepo.findByEmail(logUser.getEmail()).orElse(null);
    	
    	if (userInDb == null) {
    		result.rejectValue("email","exists","Invalid login attempt");
    		return null;
    	}
    	
    	if (!BCrypt.checkpw(logUser.getPassword(), userInDb.getPassword())) {
    		result.rejectValue("email","auth","Invalid login attempt");
    	}
    	
        if (result.hasErrors()) {
        	return null;
        }
        return userInDb;
    }
}