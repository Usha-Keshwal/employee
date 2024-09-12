package com.employee.service.Impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.employee.entity.Employee;
import com.employee.exception.EmployeeInactiveException;
import com.employee.exception.EmployeeNotFoundException;
import com.employee.helper.ExcelHelper;
import com.employee.repository.EmployeeRepo;
import com.employee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	public void save(MultipartFile file) {

		try {
			List<Employee> employees = ExcelHelper.convertExcelToListOfEmployee(file.getInputStream());
			this.employeeRepo.saveAll(employees);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<Employee> getAllEmployees() {
		return this.employeeRepo.findAll();
	}

	@Override
	public boolean deleteEmployee(Integer id) {
		// Check if employee exists
		if (employeeRepo.existsById(id)) {
			employeeRepo.deleteById(id);
			return true; // Successfully deleted
		}
		return false; // Employee not found
	}

	@Override
	public Employee getEmployeeById(Integer id) {
		Employee emp = employeeRepo.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
		if ("inactive".equalsIgnoreCase(emp.getEmpStatus())) {
			throw new EmployeeInactiveException("Employee has left the organization");
		}
		return emp;
	}

}
