package com.employee.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	@Id
	private Integer emptId;
	private String empName;
	private String empDepartment;
	private Date employmentDate;
	private String empStatus;

	public Date getEmploymentDate() {
		return employmentDate;
	}

	public void setEmploymentDate(Date employmentDate) {
		this.employmentDate = employmentDate;
	}

	public Integer getEmptId() {
		return emptId;
	}

	public void setEmptId(Integer emptId) {
		this.emptId = emptId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDepartment() {
		return empDepartment;
	}

	public void setEmpDepartment(String empDepartment) {
		this.empDepartment = empDepartment;
	}

	

	public String getEmpStatus() {
		return empStatus;
	}

	public void setEmpStatus(String empStatus) {
		this.empStatus = empStatus;
	}

	public Employee(Integer emptId, String empName, String empDepartment, Date employmentDate, String empStatus) {
		super();
		this.emptId = emptId;
		this.empName = empName;
		this.empDepartment = empDepartment;
		this.employmentDate = employmentDate;
		this.empStatus = empStatus;
	}

	public Employee() {

	}

}
