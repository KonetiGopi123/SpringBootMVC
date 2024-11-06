package com.ratan.springbootResthospital.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ratan.springbootResthospital.model.Hospital;

import jakarta.transaction.Transactional;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long> 
{


	Optional<Hospital> findByName(String name);
	
	 
    @Transactional
	void deleteByAddress(String address);
	
    @Query("SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END FROM Hospital h WHERE h.address = :address")
    boolean existsByAddress(@Param("address") String address);



   
    

}
