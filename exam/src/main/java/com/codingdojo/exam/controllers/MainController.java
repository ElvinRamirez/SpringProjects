package com.codingdojo.exam.controllers;


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

import com.codingdojo.exam.models.LoginUser;
import com.codingdojo.exam.models.Song;
import com.codingdojo.exam.models.User;
import com.codingdojo.exam.repositories.SongRepository;
import com.codingdojo.exam.services.SongService;
import com.codingdojo.exam.services.UserService;




@Controller
public class MainController {
	
	@Autowired
	private UserService userServ;
	@Autowired 
	private SongService songServ;
	@Autowired
	private SongRepository songRepo;
	
	
	@GetMapping("/")
    public String index(Model model) {
    

        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index.jsp";
    }
	
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        

        User u = userServ.register(newUser, result);
        if(result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "index.jsp";
        }
        
        //If No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
        session.setAttribute("loggedIn",u.getId());
    
        return "redirect:/home";
    }
    

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        

    	User loggingIn = userServ.login(newLogin, result);
    	
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        session.setAttribute("loggedIn", loggingIn.getId());
   
    
        return "redirect:/home";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("loggedIn");
    	return "redirect:/";
    }
    
    @GetMapping("/home")
    public String dashboard(Model model,HttpSession session) {
    	if (session.getAttribute("loggedIn") == null) {
    		return "redirect:/";
    	}
    	//always type cast session to a Long
    	model.addAttribute("songs",songServ.all());
    	model.addAttribute("user", userServ.find((Long)session.getAttribute("loggedIn")));
    	return "dashboard.jsp";
    }
    
    @GetMapping("/songs/new")
    public String newSong(@ModelAttribute("song") Song song, Model model, HttpSession session) {
    	
    	User user = userServ.find((Long)session.getAttribute("loggedIn"));
    	model.addAttribute("users", user);
    	model.addAttribute("songs",song);
    	return "addsong.jsp";
    }
    
    @PostMapping("/createSong")
    public String createSong(@Valid @ModelAttribute("song") Song song, BindingResult result) {

    	if (result.hasErrors()) {
    		return "addsong.jsp";
    	}
    	
    	//Only save songs with titles not in database
    	if(songRepo.existsByTitle(song.getTitle())) {
    	    System.out.println("Title in database");
    	    return "addsong.jsp";
    	}
    	songServ.create(song);
    	
    	return "redirect:/home";
    }
    
    //show specific song
    @GetMapping("/songs/{id}")
    public String showPage(Model model, @PathVariable("id") Long id, HttpSession session) {
    	if(session.getAttribute("loggedIn") == null) {
    		return "redirect:/";
    	}
    	//progam thinks name is a number
    	Song song = songServ.findById(id);
    	model.addAttribute("songs", song);
    	//progam thinks name is a number
    	model.addAttribute("user", userServ.find((Long)session.getAttribute("loggedIn")));
    	
    	return "oneSong.jsp";
    }
    
    //show edit page
    @GetMapping("/songs/{id}/edit")
    public String editPage(Model model, @PathVariable("id") Long id, HttpSession session) {
    	
    	if(session.getAttribute("loggedIn") == null) {
    		return "redirect:/";
    	}
    	
    	Song song = songServ.findById(id);
    	model.addAttribute("songs", song);

    	
    	return "edit.jsp";
    }
    
    //handle edit
    @PutMapping("/song/{id}")
    public String editSong(@Valid @ModelAttribute("song") Song song, BindingResult result, Model model) {
    	
    	if (result.hasErrors()) {
    		return "edit.jsp";
    	}
    	model.addAttribute("song", song);
    	
    	Integer edit = song.getTimesEdited();
    	song.setTimesEdited(edit+1);
    	System.out.println(song.getTimesEdited());
    	
        songServ.update(song);
    	return "redirect:/home";
    }
    
	@DeleteMapping("/songs/{id}/delete")
	public String delete(@PathVariable Long id) {
		songServ.destroy(id);
	return "redirect:/home";
	}
    
    }

