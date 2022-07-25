package com.example.mongodb.repository;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Repository;

import com.example.mongodb.collections.Company;
import com.example.mongodb.collections.Employee;
import com.example.mongodb.collections.Person;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.bulk.BulkWriteResult;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class EmployeeRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public Employee save(Employee emp) {
		//return mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED,Employee.class).updateMulti(updates).execute().getModifiedCount();
		 
		return mongoTemplate.save(emp);

	}

	public List<Employee> findAll() {
		return mongoTemplate.findAll(Employee.class);
	}

	public long delete(Employee emp) {
		return mongoTemplate.remove(emp).getDeletedCount();
	}

	public List<Employee> findBySalary(Integer salary) {
		Query query = new Query(Criteria.where("salary").is(salary));
		query.fields().exclude("joiningDate");
		return mongoTemplate.find(query, Employee.class);
	}

	public Page<Employee> getAllEmployees(Pageable pageable) {
		Query cq = new Query();
		Query query = new Query().with(pageable);
		List<Employee> le = mongoTemplate.find(query, Employee.class);
		log.info("Count : {}",le.size());
		return PageableExecutionUtils.getPage(le, pageable, () -> mongoTemplate.count(cq, Employee.class));
	}

}
