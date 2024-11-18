package com.sathya.exchange.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathya.exchange.model.ExchangeCurrency;

@Repository

public interface ExchangeRepository extends JpaRepository<ExchangeCurrency, Long>{

	  Optional<ExchangeCurrency> findByFromCurrencyAndToCurrency(String from, String to);
}
