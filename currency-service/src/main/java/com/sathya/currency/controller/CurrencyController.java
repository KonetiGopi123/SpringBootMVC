package com.sathya.currency.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sathya.currency.feign.FeignClientCode;
import com.sathya.currency.model.CurrencyConversion;
import com.sathya.currency.repository.CurrencyRepository;
import com.sathya.exchange.model.ExchangeCurrency;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/api/v1")

public class CurrencyController 
{
	@Autowired
	CurrencyRepository  currencyRepository;
	
	@Autowired
	FeignClientCode feignClientCode;
 
	
	
	
	@GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
	@CircuitBreaker(name = "currencyService", fallbackMethod = "currencyExchangeFallback")

	public ResponseEntity<CurrencyConversion>calculateCurrencyConversion(@PathVariable String from,@PathVariable String to,@PathVariable int quantity)
	{
	    ExchangeCurrency exchangeCurrency =feignClientCode.retrievedExchangeValue(from, to);
		
	    double conversionMultiple = exchangeCurrency.getConversionMultiple();
		
		
		
		double totalCalculatedAmount=quantity*conversionMultiple;
		
		CurrencyConversion currencyConversion=new CurrencyConversion();
		currencyConversion.setFrom(from);
		currencyConversion.setTo(to);
		currencyConversion.setQuantity(quantity);
		currencyConversion.setConversionMultiple(conversionMultiple);
		currencyConversion.setTotalCalculatedAmount(totalCalculatedAmount);
		currencyConversion.setLocalDateTime(LocalDateTime.now());
		
		currencyRepository.save(currencyConversion);
		
		
		return ResponseEntity.status(HttpStatus.OK)
		                     .header("value","amount calculated")
		                     .body(currencyConversion);
		
		
		
	}
	
	public ResponseEntity<CurrencyConversion> currencyExchangeFallback(
	        String from, String to, int quantity, Throwable ex) {

	    // Log the exception if needed
	    System.out.println("Fallback triggered for from = " + from + ", to = " + to + " due to " + ex.getMessage());

	    // Provide a default response or a custom fallback response
	    ExchangeCurrency fallbackResponse = new ExchangeCurrency();
	    fallbackResponse.setFromCurrency(from);
	    fallbackResponse.setToCurrency(to);
	    fallbackResponse.setConversionMultiple(0.0);  // Default value if the service is down
	    
	    CurrencyConversion currencyConversion = new CurrencyConversion();
	    currencyConversion.setFrom(from);
	    currencyConversion.setTo(to);
	    currencyConversion.setQuantity(quantity);
	    currencyConversion.setConversionMultiple(fallbackResponse.getConversionMultiple());
	    currencyConversion.setTotalCalculatedAmount(quantity * fallbackResponse.getConversionMultiple());
	    currencyConversion.setLocalDateTime(LocalDateTime.now());
	    
	    // You can optionally log or save the failed conversion if needed
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .header("value", "Fallback: currency conversion failed due to service unavailability")
	            .body(currencyConversion);
	}
	
	
	

}
