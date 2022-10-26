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
    	model.addAttribute("user", user);
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
    	Song song = songServ.findById(id);
    	model.addAttribute("song", song);
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
    	model.addAttribute("song", song);
    	
    	return "edit.jsp";
    }
    
    //handle edit
    @PutMapping("/song/{id}")
    public String editSong(@Valid @ModelAttribute("song") Song song,@PathVariable("id") Long id, BindingResult result, Model model) {
    	
    	if (result.hasErrors()) {
    		return "edit.jsp";
    	}
    	model.addAttribute("song", song);
    	
    	//need to record the user who made the edit
    	//Collaborators = user who updated
    	//will need to loop through a list of users who updated 
    	//will need a manyToMany relationship
    	

    	songServ.increment(song.getTitle(),song.getGenre(),song.getLyrics(),id);
    	
    	//songServ.update(song);
    	//System.out.println(song.getTimesEdited());
        //System.out.println(song.getTitle());
        //System.out.println(songServ.increment(song));
    	return "redirect:/home";
    }
    
    
	@DeleteMapping("/songs/{id}/delete")
	public String delete(@PathVariable Long id) {
		songServ.destroy(id);
	return "redirect:/home";
	}
    
    }

