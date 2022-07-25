package com.example.mongodb.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mongodb.collections.GroceryItem;
import com.example.mongodb.repository.ItemRepository;
import com.example.mongodb.service.GroceryService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GroceryServiceImpl implements GroceryService {

	@Autowired
	ItemRepository groceryItemRepo;

	@Override
	public void createGroceryItems() {
		log.info("Data creation started...");

		groceryItemRepo.save(new GroceryItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
		groceryItemRepo.save(new GroceryItem("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
		groceryItemRepo.save(new GroceryItem("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices"));
		groceryItemRepo.save(new GroceryItem("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
		groceryItemRepo.save(new GroceryItem("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));

		log.info("Data creation complete...");
	}

	// READ
	// 1. Show all the data
	@Override
	public List<GroceryItem> showAllGroceryItems() {
		return groceryItemRepo.findAll();
	}

	// 2. Get item by name
	@Override
	public GroceryItem getGroceryItemByName(String name) {
		log.info("Getting item by name: " + name);
		return  groceryItemRepo.findItemByName(name);
	}

	// 3. Get name and items of a all items of a particular category
	@Override
	public List<GroceryItem> getItemsByCategory(String category) {
		log.info("Getting items for the category " + category);
		return groceryItemRepo.findAll(category);
	}

	// 4. Get count of documents in the collection
	@Override
	public long findCountOfGroceryItems() {
		return groceryItemRepo.count();
		
	}

	// UPDATE APPROACH 1: Using MongoRepository
	@Override
	public void updateCategoryName(String category) {

		// Change to this new value
		String newCategory = "munchies";

		// Find all the items with the category
		List<GroceryItem> list = groceryItemRepo.findAll(category);

		list.forEach(item ->
		// Update the category in each document
		item.setCategory(newCategory));

		// Save all the items in database
		List<GroceryItem> itemsUpdated = groceryItemRepo.saveAll(list);

		if (itemsUpdated != null)
			log.info("Successfully updated " + itemsUpdated.size() + " items.");
	}

	// DELETE
	@Override
	public void deleteGroceryItem(String id) {
		groceryItemRepo.deleteById(id);
		log.info("Item with id " + id + " deleted...");
	}

	public String getItemDetails(GroceryItem item) {

		log.info("Item Name: " + item.getName() + ", \nItem Quantity: " + item.getQuantity() + ", \nItem Category: "
				+ item.getCategory());

		return "";
	}
}
