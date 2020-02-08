package com.example.flo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class FloApplication {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "Happy Coding!";
	}

	public static void main(String[] args) {
		SpringApplication.run(FloApplication.class, args);
	}
}
