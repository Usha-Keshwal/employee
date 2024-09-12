package com.employee.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.employee.entity.Employee;

public interface EmployeeService {
	public void save(MultipartFile file);

	List<Employee> getAllEmployees();

	boolean deleteEmployee(Integer id);
	
	Employee getEmployeeById(Integer id);

}
