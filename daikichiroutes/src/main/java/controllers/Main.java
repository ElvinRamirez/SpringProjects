package controllers;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Main {
	
	@GetMapping("/omikuji")
	public String omikuji(){
		return "index.jsp";
	}

	@GetMapping("/")
	public String index() {
		return "redirect:/omikuji";
	}
}
	

