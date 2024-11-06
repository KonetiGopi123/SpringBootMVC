package com.ratan.springbootRestnew.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratan.springbootRestnew.models.Employee;
import com.ratan.springbootRestnew.repository.EmployeeRepository;

@Service
public class EmployeeService 
{
	@Autowired
	EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee employee)
	{
		Employee emp=employeeRepository.save(employee);
		
		return emp;
	}
    public List<Employee> saveAll(List<Employee> employee) 
    {
		
		List<Employee> emps = employeeRepository.saveAll(employee);
		return emps;
		
	}
	public List<Employee> getAllEmployees() 
	{
		List<Employee>emps=employeeRepository.findAll();
		return emps;
	}
	public Optional<Employee> getById(Long id) 
	{
		Optional<Employee> optionalEmp=employeeRepository.findById(id);
		return optionalEmp;
	}
	
	
	public Optional<Employee> findByEmail(String email) {
		Optional<Employee>optionalEmp=employeeRepository.findByEmail(email);
		return optionalEmp;
	}
	public boolean deleteById(Long id) 
	{
		boolean status=employeeRepository.existsById(id);
		if(status)
		{
			employeeRepository.deleteById(id);
		return true;
	}
		else {
	   return false;
		}
	
}
	public boolean deleteByEmail(String email) 
	{
		boolean status=employeeRepository.existsByEmail(email);
		if(status)
		{
		return true;
		}
		else
		{
			return false;
		}
	}
	public void deleteall() 
	{
		employeeRepository.deleteAll();
		
	}
	public Optional<Employee> updatedEmployee(Long id, Employee newEmployee)
	{
		Optional<Employee> optionalEmp=employeeRepository.findById(id);
		if(optionalEmp.isPresent())
		{
			Employee existingEmployee=optionalEmp.get();
			existingEmployee.setName(newEmployee.getName());
			existingEmployee.setSalary(newEmployee.getSalary());
			existingEmployee.setDept(newEmployee.getDept());
			existingEmployee.setAddress(newEmployee.getAddress());
			existingEmployee.setEmail(newEmployee.getEmail());
			
			Employee updatedEmployee=employeeRepository.save(existingEmployee);
			
			return Optional.of(updatedEmployee);




			
		}
		else {
		return Optional.empty();
		}
	}
	public Optional<Employee> partialUpdate(Long id, Map<String, Object> updates) {
		Optional<Employee>optionalEmp = employeeRepository.findById(id);
		
		if(optionalEmp.isPresent())
		{
			Employee existingEmployee = optionalEmp.get();
			updates.forEach((key,value)->{
				
				switch(key) {
				case "name":
					existingEmployee.setName((String)value);
					break;
				case "address":
					existingEmployee.setAddress((String) value);
					break;
				case "salary":
					existingEmployee.setSalary((Double)value);
					break;
				case "dept":
					existingEmployee.setDept((String)value);
					break;
				case "email":
					existingEmployee.setEmail((String)value);
				}
			});
			Employee updatedEmployee = employeeRepository.save(existingEmployee);
			return Optional.of(updatedEmployee);
		}
		else {
			return Optional.empty();
		}
		
	}
	
	
}




	

