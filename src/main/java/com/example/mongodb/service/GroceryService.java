package com.example.mongodb.service;

import java.util.List;

import com.example.mongodb.collections.GroceryItem;

public interface GroceryService {

	void createGroceryItems();

	void deleteGroceryItem(String id);

	void updateCategoryName(String category);

	long findCountOfGroceryItems();

	List<GroceryItem> getItemsByCategory(String category);

	GroceryItem getGroceryItemByName(String name);

	List<GroceryItem> showAllGroceryItems();

}
