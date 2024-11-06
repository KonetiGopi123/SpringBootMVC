package com.ratan.springbootRestnew.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ratan.springbootRestnew.models.Employee;

import jakarta.transaction.Transactional;


public interface EmployeeRepository extends JpaRepository<Employee, Long> 
{

	Optional<Employee>findByEmail(String email);
	
	@Query("SELECT COUNT(e) > 0 FROM Employee e WHERE e.email = :email")
	boolean existsByEmail(@Param("email") String email);
	
	@Transactional
	void deleteByEmail(String email);
	

}
