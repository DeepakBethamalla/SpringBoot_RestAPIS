package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.EmployeeNotFoundException;
import com.example.demo.Dao.EmployeeDao;
import com.example.demo.Entity.Employee;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeDao employeeDao;

	//Get all employees
	public List<Employee> getEmployees() {
		
		return employeeDao.findAll();
	}

	public Employee addEmployee(Employee employee) {
		Employee data = employeeDao.save(employee);
		return data;
	}

	public Employee updateEmployee(Employee employee) {
		if(employee.getFirstName() == null || employee.getEmail() == null  ) {
			throw new EmployeeNotFoundException("Null values not allowed by Name and Email ");
		}else {
			Employee updated = employeeDao.save(employee);
			return updated;
		}
	}

	public String deleteEmployee(int id) {
		if(employeeDao.existsById(id)) {
			employeeDao.deleteById(id);
			return "Deleted : " + id ;
		}else {
			throw new EmployeeNotFoundException("Employee Id not found : " + id);
		}
	}

	public Optional<Employee> getEmployeeById(int id) {
		if(employeeDao.existsById(id)) {
			Optional<Employee> data = employeeDao.findById(id);
			return data;
		}else {
			throw new EmployeeNotFoundException("Employee Id not found : " + id);
		}
		
	}

}
