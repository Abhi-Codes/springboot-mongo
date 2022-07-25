package com.example.mongodb.collections;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document
@EqualsAndHashCode(callSuper=false)
public class Person extends Auditable {
	
	@Id
	private String id;
	private String name;
	private Boolean isActive;
	private String gender;
	private String favoriteFruit;
	private String eyeColor;
	private Company company;
	private List<String> tags;

}
