package com.sathya.currency.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sathya.exchange.model.ExchangeCurrency;

@FeignClient(name="exchange-currency")

public interface FeignClientCode {

	
	@GetMapping("api/v1/from/{from}/to/{to}")
	ExchangeCurrency retrievedExchangeValue(@PathVariable String from,@PathVariable String to);
}
