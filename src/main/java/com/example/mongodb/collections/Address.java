package com.example.mongodb.collections;

import lombok.Data;

@Data
public class Address {

	private String line1;
	private String line2;
	private String city;
	private String state;
	private int zipCode;
}
