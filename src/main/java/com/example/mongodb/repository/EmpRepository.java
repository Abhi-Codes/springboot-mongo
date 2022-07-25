package com.example.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.example.mongodb.collections.Employee;

@Repository
public interface EmpRepository extends MongoRepository<Employee, String>, QuerydslPredicateExecutor<Employee> {

}
