package com.example.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.example.mongodb.collections.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, String> , QuerydslPredicateExecutor<Person>{

}
