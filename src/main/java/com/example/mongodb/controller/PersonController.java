package com.example.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongodb.annotation.ApiPageable;
import com.example.mongodb.collections.Person;
import com.example.mongodb.serviceImpl.PersonServiceImpl;
import com.example.mongodb.util.SearchCriteria;
import com.example.mongodb.vo.PageableResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;


@RestController
@RequestMapping("/api/person/")
@Api(value = "APIs for person")
public class PersonController extends BaseController {

	@Autowired
	PersonServiceImpl ps;

	
	@PostMapping("/batch")
	@ApiOperation(value = "Add Person batch")
	public void createEmployeeBatch() {
		 ps.upsertBatch();
	}
	
	@GetMapping("/all")
	@ApiOperation(value = "Get all persons list")
	@ApiPageable
	public PageableResponse getAllEmployees(
			@ApiIgnore @SortDefault(sort = "_id", direction = Direction.DESC) Pageable pageable,
			@RequestParam(required = false) String[] filter, @RequestHeader("x-remote-user") String xru) {
		List<SearchCriteria> criterias = formatSearchCriteria(filter);
		Page<Person> applications = ps.getAllPersons(criterias,pageable);
		return formatPageResponse(applications);
	}
}
