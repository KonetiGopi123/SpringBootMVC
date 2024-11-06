package com.ratan.springboot.second.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookEntity 
{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	 private Long bookId;
		private String bookName;
		private double bookPrice;
		private double bookDiscount;
	    private String bookBrand;
		private String bookDescription;
		private String bookCategory;
		private LocalDate publishedAt;
		private String authorizedBy;
		

}
