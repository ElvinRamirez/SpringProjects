package com.codingdojo.dojosandninjas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.dojosandninjas.models.Dojo;
import com.codingdojo.dojosandninjas.models.Ninja;
import com.codingdojo.dojosandninjas.services.DojoService;
import com.codingdojo.dojosandninjas.services.NinjaService;



@Controller
public class MainController {
	
	@Autowired
	private DojoService dojoService;
	@Autowired
	private NinjaService ninjaService;

	@GetMapping("/dojos/new")
	//pass "dojo" to modelAttribute in the jsp form. 
	public String index(@ModelAttribute("dojo")Dojo dojo){
		return "index.jsp";
	}
	
	@PostMapping("/create")
	public String createDojo(@ModelAttribute("dojo")Dojo dojo) {
		Dojo newDojo = dojoService.createDojo(dojo);
		//redirect to the show page for the dojo just created
		return String.format("redirect:/dojos/%s",newDojo.getId());
	}
	
	@GetMapping("/dojos/{id}")
	public String showDojo(@PathVariable("id")Long id, Model model) {
		//@PathVariable to pass id in route, Model to pass dojo on jsp file
		Dojo dojo = dojoService.findById(id);
		model.addAttribute("dojo", dojo);
		return "show.jsp";
	}
	
	//show page error before creating ninja
	@GetMapping("/ninjas/new")
	public String newNinja(@ModelAttribute("ninja")Ninja ninja, Model model) {
		//allDojos needed for dropdown
		model.addAttribute("dojos", dojoService.allDojos());
		return "createNinja.jsp";
	}
	
	@PostMapping("/create/ninja")
	public String createNinja(@ModelAttribute("ninja")Ninja ninja) {
		Ninja newNinja = ninjaService.createNinja(ninja);
		return "redirect:/dojos/" + newNinja.getDojo().getId();
	}
}
