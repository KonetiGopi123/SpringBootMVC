package com.sathya.currency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathya.currency.model.CurrencyConversion;

@Repository

public interface CurrencyRepository extends JpaRepository<CurrencyConversion, Long> {

}
