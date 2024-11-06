package com.ratan.springboot.second.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.ratan.springboot.second.Entity.BookEntity;
import com.ratan.springboot.second.model.BookModel;
import com.ratan.springboot.second.repository.BookRepository;

@Service

public class BookService 
{
	@Autowired
	BookRepository bookRepository;
	
	public void saveBookData(BookModel bookModel)
	{
		double price=bookModel.getBookPrice();
		String category=bookModel.getBookCategory();
		double bprice=0.0;
		switch(category)
		{
		case "Telugu" :
			bprice=0.1*price;
			break;
		case "Hindi" :
			bprice=0.15*price;
			break;
		case "English" :
			bprice=0.2*price;
			break;
		case "Maths" :
			bprice=0.25*price;
			break;
		case "Physics" :
			bprice=0.25*price;
			break;
		case "Chemistry" :
			bprice=0.25*price;
			break;
		case "Social" :
			bprice=price*0.25;
			break;
		
		}
		
		//read the data from the model and set the data to entity
		
		BookEntity bookEntity = new BookEntity();
		bookEntity.setBookName(bookModel.getBookName());
		bookEntity.setBookPrice(bookModel.getBookPrice());
		bookEntity.setBookBrand(bookModel.getBookBrand());
		bookEntity.setBookDescription(bookModel.getBookDescription());
        bookEntity.setBookCategory(bookModel.getBookCategory());
        
        bookEntity.setBookDiscount(bprice);
        bookEntity.setPublishedAt(LocalDate.now());
        bookEntity.setAuthorizedBy(System.getProperty("user.name"));
        
        bookRepository.save(bookEntity);
     }
	public List<BookEntity>getAllBooks()
	{
	List<BookEntity>books=bookRepository.findAll();
	return books;

}
	public void deleteBookById(@RequestParam Long id)
	{
		bookRepository.deleteById(id);
	}
	public BookEntity getBookById(Long id) 
	{
        return bookRepository.findById(id)
                             .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
	
}
	
}
