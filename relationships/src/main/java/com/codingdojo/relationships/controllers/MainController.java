package com.codingdojo.relationships.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.relationships.models.License;
import com.codingdojo.relationships.models.Person;
import com.codingdojo.relationships.services.LicenseService;
import com.codingdojo.relationships.services.PersonService;


@Controller
public class MainController {
	
	@Autowired
	private PersonService personService;
	private LicenseService licenseService;
	
	@GetMapping("/persons/{person_id}")
	public String showPerson(@PathVariable Long person_id, Model model) {
	    
	    Person someAwesomePerson = personService.findById(person_id);
	    model.addAttribute("person", someAwesomePerson);
	    
	    return "showPerson.jsp";
	}
	
	@PostMapping("/licenses")
	public String licenses(@Valid @ModelAttribute("license") License license,
			BindingResult result, Model model) {
	    // error handling with binding result omitted    
		if (result.hasErrors()) {
			model.addAttribute("licenseService", licenseService.allLicenses());
			return "index.jsp";
		}
	    licenseService.createLicense(license); // Already has the person!
	        
	    return "redirect:/persons";
	}
	
}
