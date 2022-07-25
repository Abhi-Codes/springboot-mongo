package com.example.mongodb.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.mongodb.collections.Employee;
import com.example.mongodb.predicate.CommonPredicateBuilder;
import com.example.mongodb.repository.EmpRepository;
import com.example.mongodb.repository.EmployeeRepository;
import com.example.mongodb.service.EmployeeService;
import com.example.mongodb.util.SearchCriteria;
import com.querydsl.core.BooleanBuilder;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository er;
	
	@Autowired
	EmpRepository emp;

	@Override
	public Employee save(Employee emp) {
		emp.setJoiningDate(new Date());
		return er.save(emp);
		
	}

	@Override
	public long delete(Employee emp) {
		return er.delete(emp);
		
	}

	@Override
	public List<Employee> find() {
		return er.findAll();
	}

	@Override
	public List<Employee> getEmployeeBySalary(Integer salary) {
		return er.findBySalary(salary);
	}

	@Override
	public Page<Employee> getAllEmployees(List<SearchCriteria> criterias,Pageable pageable) {
		BooleanBuilder exp = getApplicationListingFilterExp(criterias);
		return emp.findAll(exp, pageable);
	}
	
	
	public BooleanBuilder getApplicationListingFilterExp(List<SearchCriteria> criterias) {
		BooleanBuilder exp = new BooleanBuilder();
		for (SearchCriteria criteria : criterias) {
			exp = exp.and(new CommonPredicateBuilder<>(Employee.class).and(criteria).replaceKeyMap(getApplicationFilterReplaceKeyMap()).build());
			
		}
		return exp;
	}
	
	public Map<String, String> getApplicationFilterReplaceKeyMap() {
		Map<String, String> replaceKeyMap = new HashMap<>();
		replaceKeyMap.put("addressCity", "address.city");
		replaceKeyMap.put("addressState", "address.state");
		return replaceKeyMap;
	}


}
