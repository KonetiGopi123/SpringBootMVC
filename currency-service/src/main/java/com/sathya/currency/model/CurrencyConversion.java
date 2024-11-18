package com.sathya.currency.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class CurrencyConversion 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "from_currency")
	private String from;
	@Column(name = "to_currency")
	private String to;
	private int quantity;
	private double conversionMultiple;
	private double totalCalculatedAmount;
	private LocalDateTime localDateTime;
	

}
