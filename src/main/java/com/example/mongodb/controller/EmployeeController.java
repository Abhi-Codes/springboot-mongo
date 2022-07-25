package com.example.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongodb.annotation.ApiPageable;
import com.example.mongodb.collections.Employee;
import com.example.mongodb.service.EmployeeService;
import com.example.mongodb.util.SearchCriteria;
import com.example.mongodb.vo.PageableResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/api/employee/")
@Api(value = "APIs for employee")
public class EmployeeController extends BaseController {

	@Autowired
	EmployeeService es;

	@PostMapping
	@ApiOperation(value = "Add Employee")
	public Employee createEmployee(@RequestBody Employee emp) {
		return es.save(emp);
	}
	

	@DeleteMapping
	@ApiOperation(value = "delete employee")
	public long deleteEmployee(@RequestBody Employee emp) {
		return es.delete(emp);
	}

	@PutMapping
	@ApiOperation(value = "update employee")
	public Employee updateEmployee(@RequestBody Employee emp) {
		return es.save(emp);
	}

	@GetMapping
	@ApiOperation(value = "find all employees")
	public List<Employee> find() {
		return es.find();
	}

	@GetMapping("getEmployeeBySalary")
	@ApiOperation(value = "getEmployeeBySalary")
	public List<Employee> getEmployeeBySalary(@RequestParam(required = true) Integer salary) {
		return es.getEmployeeBySalary(salary);
	}
	
	@GetMapping("/all")
	@ApiOperation(value = "Get all employee list")
	@ApiPageable
	public PageableResponse getAllEmployees(
			@ApiIgnore @SortDefault(sort = "_id", direction = Direction.DESC) Pageable pageable,
			@RequestParam(required = false) String[] filter, @RequestHeader("x-remote-user") String xru) {
		List<SearchCriteria> criterias = formatSearchCriteria(filter);
		Page<Employee> applications = es.getAllEmployees(criterias,pageable);
		return formatPageResponse(applications);
	}
}
