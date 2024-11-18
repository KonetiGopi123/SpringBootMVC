package com.ratan.springbootResthospital.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratan.springbootResthospital.model.Hospital;
import com.ratan.springbootResthospital.repository.HospitalRepository;

@Service
public class HospitalService 
{
	@Autowired
	HospitalRepository hospitalRepository;

	

	public Hospital save(Hospital hospital) 
	{
		Hospital hos=hospitalRepository.save(hospital);
		return hos;
	}



	public List<Hospital> saveAll(List<Hospital> hospital) 
	{
		List<Hospital> hos=hospitalRepository.saveAll(hospital);
		return hos;
	}



	public List<Hospital> getAllHospital() 
	{
		List<Hospital> hos=hospitalRepository.findAll();
		return hos;
	}



	public Optional<Hospital> getbyid(Long id) 
	{
		Optional<Hospital> optionalhos=hospitalRepository.findById(id);
		return optionalhos;
	}



	



	public Optional<Hospital> getbyname(String name) 
	{
		Optional<Hospital>optionalhos= hospitalRepository.findByName(name);
		return optionalhos;		
	}



	public boolean deletebyid(Long id) 
	{
		boolean status=hospitalRepository.existsById(id);
		if(status)
		{
			hospitalRepository.deleteById(id);
			return true;
		}
		else {
		return false;
		}
		
	}
    public boolean deletebyaddress(String address) 
	{
		boolean status=hospitalRepository.existsByAddress(address);
		if(status)
		{
			hospitalRepository.deleteByAddress(address);
			return true;
		}
		else {
		return false;
		}
	}
    public Optional<Hospital> updatedHospital(Long id, Hospital newhospital) 
	{
		Optional<Hospital> optionalhos=hospitalRepository.findById(id);
		if(optionalhos.isPresent())
		{
			Hospital existingHopsital=optionalhos.get();
			existingHopsital.setName(newhospital.getName());
			existingHopsital.setMobile(newhospital.getMobile());
			existingHopsital.setSpecialization(newhospital.getSpecialization());
			existingHopsital.setAddress(newhospital.getAddress());
			existingHopsital.setRating(newhospital.getRating());
			
			
           Hospital updatedHospital=hospitalRepository.save(existingHopsital);
			
			return Optional.of(updatedHospital);

		}
		else 
		{
		
		return Optional.empty();
		}
	}

		
	
public Optional<Hospital> updatedHospital(Long id,Map<String,Object>updates)
{
	Optional<Hospital> updatedHospital=hospitalRepository.findById(id);
	
	if(updatedHospital.isPresent())
	{
		Hospital existingHospital=updatedHospital.get();
        updates.forEach((key,value)->{
			
			switch(key) {
			case "name":
				existingHospital.setName((String)value);
				break;
			case "address":
				existingHospital.setAddress((String) value);
				break;
			case "Mobile":
				existingHospital.setMobile((long)value);
				break;
			case "Specialization":
				existingHospital.setSpecialization((String)value);
				break;
			case "rating":
				existingHospital.setRating((Double)value);
			}
		});
		Hospital updatedHospital1 = hospitalRepository.save(existingHospital);
		return Optional.of(updatedHospital1);
	}
	else {
		return Optional.empty();
	}
}



public Optional<Hospital> partialUpdate(Long id, Map<String, Object> updates) {
	// TODO Auto-generated method stub
	return null;
}
}
	

    


