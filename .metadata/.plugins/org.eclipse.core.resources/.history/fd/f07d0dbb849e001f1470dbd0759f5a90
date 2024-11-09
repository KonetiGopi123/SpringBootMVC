package com.ratan.react.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String Name;
	private double Quantity;
	private double Price;
	private String Category;
	private String Image;
	

}
