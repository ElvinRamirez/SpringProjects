package com.codingdojo.omikujiform.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Main {
	
	@GetMapping("/")
	public String index() {
		return "redirect:/omikuji";
	}
	
	@GetMapping("/omikuji")
	public String omikuji() {
		return "index.jsp";
	}
	
	@PostMapping("/formData")
	public String process(
			HttpSession session,
			@RequestParam("years")int years,
			@RequestParam("city")String city,
			@RequestParam("person")String person,
			@RequestParam("hobby")String hobby,
			@RequestParam("animal")String animal,
			@RequestParam("message")String message){
		
		String fortune = String.format(
				"In %s years you will live in %s with %s as your roommate, %s for a living."
				+ " The next time you see a %s, you will have good luck. Also, %s",
				years,city,person,hobby,animal,message);
		
		session.setAttribute("fortune",fortune);
		return "redirect:/omikuji/show";
	}
	
	@GetMapping("/omikuji/show")
	public String show(HttpSession session, Model model) {
		
		String result = (String) session.getAttribute("fortune");
		model.addAttribute("result", result);
		return "show.jsp";
	}
			
}
