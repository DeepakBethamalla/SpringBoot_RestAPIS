package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.EmployeeNotFoundException;
import com.example.demo.Entity.ErrorEntity;

@ControllerAdvice
public class ErrorController {

	@ExceptionHandler
	public ResponseEntity<ErrorEntity> handleException(EmployeeNotFoundException err){
		ErrorEntity error = new ErrorEntity();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(err.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorEntity> handleException(Exception err){
		ErrorEntity error = new ErrorEntity();
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMessage(err.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
	}

}
