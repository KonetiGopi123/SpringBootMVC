package com.ratan.springbootRestnew.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratan.springbootRestnew.exception.EmployeeNotFoundException;
import com.ratan.springbootRestnew.models.Employee;
import com.ratan.springbootRestnew.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController 
{
	@Autowired
	EmployeeService employeeService;
	
	@PostMapping("/saveemployee")
	public Employee saveEmployee(@Valid  @RequestBody Employee employee)
	{
		 Employee emp= employeeService.saveEmployee(employee);
		return emp;
		
		
		
	}
	@PostMapping("/saveall")
	public ResponseEntity <List<Employee>>saveAll(@RequestBody List<Employee> employee) {
		
		List<Employee> saveAll= employeeService.saveAll(employee);
		
		return  ResponseEntity.status(HttpStatus.CREATED)
				.header("employeeStatus", "employee saved successfully")
				 .body(saveAll);
		
	}
	@GetMapping("/getall")
	public ResponseEntity<List<Employee>>getAllEmployees()
	{
		List<Employee>emps=employeeService.getAllEmployees();
		
		return ResponseEntity.status(HttpStatus.OK)
				             .header("status", "data reading is successfull")
				             .body(emps);
	}
	@GetMapping("/getById/{id}")
	public ResponseEntity<?> getById(@PathVariable Long id)
	{
		Optional<Employee> optionalEmp=employeeService.getById(id);
		if(optionalEmp.isPresent())
		{
			Employee employee = optionalEmp.get();
			 // Create an EntityModel for the user
	        EntityModel<Employee> entityModel = EntityModel.of(employee);

	        // Add self link
	        entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).getById(id)).withSelfRel());

	        // Add link to update the user
	        entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).updateById(id, employee)).withRel("update"));

	        // Add link to delete the user
	        entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).deleteById(id)).withRel("delete"));

	        // Add link to get all users
	        entityModel.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(EmployeeController.class).getAllEmployees()).withRel("all-users"));
			return ResponseEntity.status(HttpStatus.OK)
					.body(optionalEmp);
		}
		else {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND)
					           //  .body("Emp is not found with id.."+id);
			throw new EmployeeNotFoundException("Employee not found with id"+id);
					             
		}
	}
	private Class<?> updateById(Long id, Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}
	@GetMapping("/getByEmail/{email}")
	public ResponseEntity<?> getByEmail(@PathVariable String email)
	{
		Optional<Employee>optionalEmp=employeeService.findByEmail(email);
		if(optionalEmp.isPresent())
		{
			return ResponseEntity.status(HttpStatus.OK)
					             .body(optionalEmp);
		}
		else {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND)
					            // .body("emp is not found with email"+email);
			
			throw new EmployeeNotFoundException("Employeee not found with email"+email);
		}
	}
	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id)
	{
		 boolean status=employeeService.deleteById(id);
		 if(status)
		 {
			 return ResponseEntity.noContent().build();	 
		 }
		 else
		 {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND)
		             .header("status", "data is not found")
		             .body("data not found with id"+id);
		 }
	}
	@DeleteMapping("/deletebyemail/{email}")
	public ResponseEntity<?> deleteByEmail(@PathVariable String email)
	{
		boolean status=employeeService.deleteByEmail(email);
		if(status)
		{
			return ResponseEntity.noContent().build();
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					             .header("status", "data is not found")
					             .body("data is not found with email"+email);
		}
	}
	@DeleteMapping("/deleteall")
	public void deleteall()
	{
		employeeService.deleteall(); 
	}
	@PutMapping("/updatebyid/{id}")
	public ResponseEntity<?> updatedEmployee(@PathVariable Long id,@RequestBody Employee newEmployee)
	{
		Optional<Employee>optionalEmp=employeeService.updatedEmployee(id,newEmployee);
		if(optionalEmp.isPresent())
		{
			return ResponseEntity.status(HttpStatus.OK)
					             .header("status", "updated successfully")
					             .body(optionalEmp);
			
		}
			
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				             .body("updated successfully with id"+id);
		
	}
	@PatchMapping("/partialupdate/{id}")
	public ResponseEntity<?> partialUpdate(@PathVariable Long id, @RequestBody Map<String,Object> updates)
	{
		Optional<Employee>updatedEmployee = employeeService.partialUpdate(id,updates);
		if(updatedEmployee.isPresent())
		{
			return ResponseEntity.status(HttpStatus.OK)
					.body(updatedEmployee);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
								.body("no data found  with this id.."+id);
		}
	}
	
	
}
	

	


