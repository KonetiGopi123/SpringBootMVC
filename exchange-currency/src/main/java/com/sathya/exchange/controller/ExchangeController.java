package com.sathya.exchange.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathya.exchange.model.ExchangeCurrency;
import com.sathya.exchange.repository.ExchangeRepository;

@RestController
@RequestMapping("/api/v1")

public class ExchangeController 
{
	@Autowired
	ExchangeRepository exchangeRepository;
	
	@GetMapping("/from/{from}/to/{to}")
	public ResponseEntity<ExchangeCurrency> retrievedExchangeValue(@PathVariable String from,@PathVariable String to)
	{
		Optional<ExchangeCurrency> optionalExchangeCurrency=exchangeRepository.findByFromCurrencyAndToCurrency(from,to);
				if(optionalExchangeCurrency.isPresent())
				{
					ExchangeCurrency exchangeCurrency=optionalExchangeCurrency.get();
					
					return ResponseEntity.status(HttpStatus.OK)
							.header("value", "value stored successfully")
							.body(exchangeCurrency);
				}
				else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND)
							.header("value","value not found")
							.body(null);
				}
				
	}
	

}
