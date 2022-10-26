package com.codingdojo.safetravels.controllers;


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

import com.codingdojo.safetravels.models.Travel;
import com.codingdojo.safetravels.services.TravelService;


@Controller
public class TravelController {
	
	
	@Autowired
	private TravelService travelService;

	@GetMapping("/expenses")
	public String index(@ModelAttribute("travel")Travel travel, Model model) {
		model.addAttribute("travelService", travelService.allTrips());
		return "index.jsp";
	}
	
	@GetMapping("/expenses/show/{id}")
	public String show(@PathVariable("id") Long id,Model model) {
		model.addAttribute("travelService", travelService.findTrip(id));
		return "show.jsp";
	}
	
	@PostMapping("/new")
	public String newExpense(@Valid @ModelAttribute("travel")Travel travel,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			model.addAttribute("travelService", travelService.allTrips());
			return "index.jsp";
		}
		travelService.createTrip(travel);
		
		return "redirect:/expenses";
	}
	
	@GetMapping("/expenses/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("travelService", travelService.findTrip(id));
		return "edit.jsp";
	}
	
	@PutMapping("/expenses/{id}/edit")
	public String editExpense(@Valid @ModelAttribute("travel")Travel travel,
			BindingResult result, Model model, @PathVariable("id")Long id) {
		if (result.hasErrors()) {
			model.addAttribute("travelService", travelService.allTrips());
			return "index.jsp";
		}
		travelService.updateTrip(travel);
		
		return "redirect:/expenses";
	}
	
	@DeleteMapping("/expenses/{id}/delete")
	public String delete(@PathVariable Long id) {
	travelService.destroy(id);
	return "redirect:/expenses";
	}
}
