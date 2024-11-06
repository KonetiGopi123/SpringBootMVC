package com.ratan.springbootRest.service;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratan.springbootRest.entity.EmployeEntity;
import com.ratan.springbootRest.model.EmployeModel;
import com.ratan.springbootRest.repository.EmployeRepository;

@Service
public class EmployeService 
{
	@Autowired
	EmployeRepository employeRepository;

	// This method matches the method name in your controller: saveEmp
    public EmployeEntity saveEmp(EmployeModel employeModel) 
    {
        EmployeEntity employeEntity = new EmployeEntity();
        
        // Setting basic fields from model to entity
        employeEntity.setEmpName(employeModel.getEmpName());
        employeEntity.setEmpMobile(employeModel.getEmpMobile());
        employeEntity.setEmpEmail(employeModel.getEmpEmail());
        employeEntity.setEmpDept(employeModel.getEmpDept());
        employeEntity.setBasicSalary(employeModel.getBasicSalary());

        // Calculate DA, PF, HRA
        double basicSalary = employeModel.getBasicSalary();
        double da = basicSalary * 0.1;  // Example: DA is 10% of basic salary
        double pf = basicSalary * 0.12; // Example: PF is 12% of basic salary
        double hra = basicSalary * 0.4; // Example: HRA is 40% of basic salary
        
        // Calculate total salary
        double totalSalary = basicSalary + da + hra - pf;
        
        // Set calculated fields to entity
        employeEntity.setDa(da);
        employeEntity.setPf(pf);
        employeEntity.setHra(hra);
        employeEntity.setTotalSalary(totalSalary);
        employeEntity.setJoiningDate(LocalDate.now());
        // Save entity to database
        return employeRepository.save(employeEntity);
        }

}