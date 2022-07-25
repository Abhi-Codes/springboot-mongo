package com.example.mongodb.serviceImpl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import com.example.mongodb.collections.Company;
import com.example.mongodb.collections.Person;
import com.example.mongodb.collections.QPerson;
import com.example.mongodb.predicate.CommonPredicateBuilder;
import com.example.mongodb.repository.PersonRepository;
import com.example.mongodb.util.SearchCriteria;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.bulk.BulkWriteResult;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.StringPath;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonServiceImpl {

	@Autowired
	PersonRepository pr;

	@Autowired
	MongoTemplate mongoTemplate;

	public Page<Person> getAllPersons(List<SearchCriteria> criterias, Pageable pageable) {
		BooleanBuilder exp = getPersonListingFilterExp(criterias);
		return pr.findAll(exp, pageable);
	}

	public BooleanBuilder getPersonListingFilterExp(List<SearchCriteria> criterias) {
		BooleanBuilder exp = new BooleanBuilder();
		for (SearchCriteria criteria : criterias) {
			QPerson personPath = QPerson.person;
			switch (criteria.getKey()) {
			case "term":
				BooleanExpression nameExp = personPath.name.containsIgnoreCase(criteria.getValue());
				exp = exp.and(nameExp.or(personPath.id.eq(criteria.getValue())));
				break;
			case "tags":
				ListPath<String, StringPath> lp = personPath.tags;
				exp = exp.and(lp.any().eq(criteria.getValue()));
				break;
			default:
				exp = exp.and(new CommonPredicateBuilder<>(Person.class).and(criteria)
						.replaceKeyMap(getPersonFilterReplaceKeyMap()).build());

			}
		}
		return exp;
	}

	public Map<String, String> getPersonFilterReplaceKeyMap() {
		Map<String, String> replaceKeyMap = new HashMap<>();
		replaceKeyMap.put("companyLocationCountry", "company.location.country");
		replaceKeyMap.put("companyLocationAddress", "company.location.address");
		replaceKeyMap.put("companyEmail", "company.email");
		return replaceKeyMap;
	}

	public void upsertBatch() {
		// create Object Mapper
		ObjectMapper mapper = new ObjectMapper();
		List<Person> empL = new ArrayList<>();
		// read JSON file and map/convert to java POJO
		try {
			empL = mapper.readValue(new File("src/main/resources/abc.json"), new TypeReference<List<Person>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		log.info("Size : {}", empL.size());

		List<Pair<Query, Update>> updates = new ArrayList<>(empL.size());
		empL.forEach(emp -> {
			Query query = Query.query(Criteria.where("id").is(emp.getId()));

			Update update = new Update();
			update.set("name", emp.getName());
			update.set("isActive", emp.getIsActive());
			update.set("gender", emp.getGender());
			update.set("favoriteFruit", emp.getFavoriteFruit());
			update.set("eyeColor", emp.getEyeColor());

			Company c = new Company();
			c.setEmail(emp.getCompany().getEmail());
			c.setLocation(emp.getCompany().getLocation());
			c.setPhone(emp.getCompany().getPhone());
			c.setTitle(emp.getCompany().getTitle());
			update.set("company", emp.getCompany());
			update.set("tags", emp.getTags());
			update.setOnInsert("createdAt", LocalDateTime.now());
			update.set("updatedAt", LocalDateTime.now());

			updates.add(Pair.of(query, update));
		});

		BulkOperations bulkOperations = mongoTemplate.bulkOps(BulkOperations.BulkMode.UNORDERED, Person.class);
		bulkOperations.upsert(updates);
		BulkWriteResult bwr = bulkOperations.execute();
		log.info("Size  3: {}", bwr.getUpserts().size());
		log.info("Size  4: {}", bwr.getModifiedCount());
	}

}
