package com.employee.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.employee.entity.Employee;
import com.employee.helper.ExcelHelper;
import com.employee.service.EmployeeService;

@RestController
@CrossOrigin("*")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employees")
	public ResponseEntity<Map<String, String>> upload(@RequestParam("file") MultipartFile file) {
		if (ExcelHelper.checkExcelFormat(file)) {
			// true
			this.employeeService.save(file);
			return ResponseEntity.ok(Map.of("message", "File is uploaded and data is saved to db"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Please upload a valid Excel file"));

	}

	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = this.employeeService.getAllEmployees();
		if (employees.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(employees); // HTTP 204 No Content if list is empty
		}
		return ResponseEntity.ok(employees);
	}

	@DeleteMapping("employees/{id}")
	public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable Integer id) {
		boolean isDeleted = employeeService.deleteEmployee(id);
		if (isDeleted) {
			return ResponseEntity.ok(Map.of("message", "Employee deleted successfully")); // HTTP 200 OK
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Employee not found")); // HTTP 404
																											// Not Found
		}

		// employeeService.deleteEmployee(id);
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
		Employee employee = this.employeeService.getEmployeeById(id);
		if (employee != null) {
			return ResponseEntity.ok(employee); // HTTP 200 OK
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // HTTP 404 Not Found
	}
}
