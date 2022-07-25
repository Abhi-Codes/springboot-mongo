package com.example.mongodb.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.mongodb.collections.Employee;
import com.example.mongodb.util.SearchCriteria;

public interface EmployeeService {

	Employee save(Employee emp);

	long delete(Employee emp);

	List<Employee> find();

	List<Employee> getEmployeeBySalary(Integer salary);

	Page<Employee> getAllEmployees(List<SearchCriteria> criterias, Pageable pageable);


}
