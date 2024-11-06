package com.ratan.springbootRestnew.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeErrorResponse 
{
	private LocalDateTime localDateTime;
	private String message;
	private int statuscode;

}
