package com.example.flo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.*;

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
