package com.ratan.springboot.second.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookModel 
{
	private String bookName;
	private double bookPrice;
	private String bookBrand;
	private String bookDescription;
	private String bookCategory;

}
