package com.codingdojo.hellohuman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HellohumanApplication {

	public static void main(String[] args) {
		SpringApplication.run(HellohumanApplication.class, args);
	}
	
	@RequestMapping("/")
	public String greeting(@RequestParam(value="first_name", required=false) String first_name,
						   @RequestParam(value="last_name", required=false) String last_name)
						   {
		String full_name = null;
		if(first_name == null && last_name == null) {
		full_name = "Human";
		}
		else if (first_name != null && last_name != null) {
			full_name = first_name + " " + last_name;
		}
		return String.format("Hello" + " " + full_name);
	}

}
