package com.ratan.springboot.second.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ratan.springboot.second.Entity.BookEntity;
import com.ratan.springboot.second.model.BookModel;
import com.ratan.springboot.second.service.BookService;

@Controller
public class BookController 
{
	
	@Autowired
	BookService bookService;
	
	 @GetMapping("/bookform")
	   public String getBookForm(Model model)
	   {
		   //give the form with object
		   BookModel bookModel=new BookModel();
		   bookModel.setBookPrice(800);
		   model.addAttribute("bookModel",bookModel);
		   
		   return "add-book";
	   }
	 @PostMapping("/saveBook")
	   public String saveBook(BookModel bookModel)
	   {
	   bookService.saveBookData(bookModel);
	   return "success";
	   

}
	 @GetMapping("/getBooks")
	   public String getBooks(Model model)
	   {
		   List<BookEntity> books=bookService.getAllBooks();
		   model.addAttribute("books",books);
		   return "book-List";
}
	 @PostMapping("/delete/{id}")
	   public String deleteBook(@PathVariable("id") Long bookId) {
	       bookService.deleteBookById(bookId);
	       return "redirect:/getBooks";
	 }
	 @GetMapping("/editBook/{id}")
	    public String showEditForm(@PathVariable("id") Long bookId, Model model) {
	        BookEntity book = bookService.getBookById(bookId);  // Retrieve the book by ID
	        model.addAttribute("book", book);  // Add the book to the model
	        return "editBook";
}
}
	


