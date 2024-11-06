package com.ratan.springbootResthospital.controller;

import java.net.http.HttpResponse.BodyHandler;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.ratan.springbootResthospital.model.Hospital;
import com.ratan.springbootResthospital.service.HospitalService;

@RestController
@RequestMapping("/api/v1")
public class HospitalController 
{
	@Autowired
	HospitalService hospitalService;
	
	@PostMapping("/savehospital")
	public Hospital saveHospital(@RequestBody Hospital hospital)
	{
		Hospital hos= hospitalService.save(hospital);
		return hos;
}
	@PostMapping("/saveall")
	public ResponseEntity <List<Hospital>>saveAll(@RequestBody List<Hospital> hospital) 
	{
		List<Hospital> saveAll= hospitalService.saveAll(hospital);
		
		return  ResponseEntity.status(HttpStatus.CREATED)
				.header("hospitalStatus", "data saved successfully")
				 .body(saveAll);
}
	@GetMapping("/getall")
	public ResponseEntity<List<Hospital>>getAllHospital()
	{
		List<Hospital> hos=hospitalService.getAllHospital();
		
		return ResponseEntity.status(HttpStatus.OK)
				             .header("status", "data reading is successfull")
				             .body(hos);
	}
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<?> getbyid(@PathVariable Long id)
	{
		Optional<Hospital>optionalhos=hospitalService.getbyid(id);
		if(optionalhos.isPresent())
		{
			return ResponseEntity.status(HttpStatus.OK)
					            .header("status","data reading is successfull")
					            .body(optionalhos);
					            
			
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					             
					             .body("data is not found with id"+id);
		}
	}
	@GetMapping("/getbyname/{name}")
	public ResponseEntity<?> getbyname(@PathVariable String name)
	{
		Optional<Hospital> optionalhos=hospitalService.getbyname(name);
		if(optionalhos.isPresent())
		{
			return ResponseEntity.status(HttpStatus.OK)
					             .header("status", "data reading is successfull")
					             .body(optionalhos);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				             .body("data is not found with name"+name);
	}
	@DeleteMapping("/deletebyid/{id}")
	public ResponseEntity<?> deletebyid(@PathVariable Long id)
	{
		boolean status=hospitalService.deletebyid(id);
		if(status)
		{
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
		             .header("status","data not found")
		             .body("data not found with id"+id);
		}
		
		
	}
	@DeleteMapping("/deletebyaddress/{address}")
	public ResponseEntity<?> deletebyaddress(@PathVariable String address)
	{
		boolean status=hospitalService.deletebyaddress(address);
		if(status)
		{
			return ResponseEntity.noContent().build();
			
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
		             .header("status", "data not found")
		             .body("data not found with address"+address);
			
		}
	}
	@PutMapping("/updatebyid/{id}")
	public ResponseEntity<?> updatedHospital(@PathVariable Long id, @RequestBody Hospital newHospital)
	{
		Optional<Hospital> optionalhos=hospitalService.updatedHospital(id,newHospital);
		if(optionalhos.isPresent())
		{
			return ResponseEntity.status(HttpStatus.OK)
					             .header("status","data updated successfully")
					             .body(optionalhos);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					             .body("data updated successfully with id"+id);
		}
		
		
		
	}
	@PatchMapping("/partialupdate/{id}")
	public ResponseEntity<?> partialupdate(@PathVariable Long id,@RequestBody Map<String,Object> updates)
	
	{
		Optional<Hospital>updatedHospital= hospitalService.partialUpdate(id,updates);
		if(updatedHospital.isPresent())
		{
			return ResponseEntity.status(HttpStatus.OK)
					             .body(updatedHospital);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					             .body("data not found with id"+id);
			
					
		}
		
	}
	
}
