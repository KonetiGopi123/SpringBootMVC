package com.ratan.springbootRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratan.springbootRest.entity.EmployeEntity;
import com.ratan.springbootRest.model.EmployeModel;
import com.ratan.springbootRest.service.EmployeService;

@RestController
@RequestMapping("/api/v1")
public class EmployeController 
{
	@Autowired
	EmployeService employeService;
	
	@PostMapping("/savEmploye")
	public EmployeEntity saveEmploye(@RequestBody EmployeModel employeModel)
	{
		return employeService.saveEmp(employeModel);
		
	}

}
