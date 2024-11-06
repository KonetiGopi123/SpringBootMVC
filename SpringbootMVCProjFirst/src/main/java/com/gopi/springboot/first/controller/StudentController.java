package com.gopi.springboot.first.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller

public class StudentController 
{
	@GetMapping("/bookform")
	   public String getBookForm(Model model)
	   {
		   //give the form with object
		   BookModel bookModel=new BookModel();
		   bookModel.setbookPrice(8000);
		   model.addAttribute("bookModel",bookModel);
		   
		   return "add-book";
	   }

}
