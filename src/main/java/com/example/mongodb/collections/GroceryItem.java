package com.example.mongodb.collections;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("groceryitems")
@Data
public class GroceryItem {

	@Id
	private String id;
	private String name;
	private int quantity;
	private String category;

	public GroceryItem(String id, String name, int quantity, String category) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.category = category;
	}
}