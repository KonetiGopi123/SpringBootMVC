package com.gopi.springboot.first.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentModel 
{
	private String bookName;
	private double bookPrice;
	private String bookBrand;
	private String bookDescription;
	private String bookCategory;

}
