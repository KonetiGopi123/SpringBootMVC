package com.ratan.springbootRest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor


public class EmployeModel 
{
	private String empName;
	private double basicSalary;
	private String empDept;
	private String empEmail;
	private long empMobile;

}
