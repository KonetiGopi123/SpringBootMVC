package com.ratan.springbootRestnew.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Employee
{
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	
	private Long id;
	
	@NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String name;

    @Min(value = 0, message = "Salary should be a positive number")
    private double salary;

    @NotBlank(message = "Department is mandatory")
    private String dept;

    @NotBlank(message = "Address is mandatory")
    @Size(min = 10, max = 200, message = "Address should be between 10 and 200 characters")
    private String address;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

}
