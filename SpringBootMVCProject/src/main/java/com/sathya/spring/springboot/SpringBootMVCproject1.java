package com.sathya.spring.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class SpringBootMVCproject1 
{
	@GetMapping("/check")
		public String greetInfo()
		{
		    return "greeting";
		}
		


}
