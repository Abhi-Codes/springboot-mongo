package com.example.mongodb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongodb.collections.GroceryItem;
import com.example.mongodb.service.GroceryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/grocery/")
@Api(value = "APIs for grocery")
public class GroceryController {

	@Autowired
	GroceryService gs;

	@PostMapping
	@ApiOperation(value = "Add items")
	public void createGroceryItems() {
		gs.createGroceryItems();
	}

	@DeleteMapping
	@ApiOperation(value = "delete item")
	public void deleteGroceryItem(@RequestParam(required = true) String id) {
		gs.deleteGroceryItem(id);
	}

	@PutMapping
	@ApiOperation(value = "update item")
	public void updateCategoryName(@RequestParam(required = true) String category) {
		gs.updateCategoryName(category);
	}

	@GetMapping("findCountOfGroceryItems")
	@ApiOperation(value = "findCountOfGroceryItems")
	public long findCountOfGroceryItems() {
		return gs.findCountOfGroceryItems();
	}

	@GetMapping("getItemsByCategory")
	@ApiOperation(value = "getItemsByCategory")
	public List<GroceryItem> getItemsByCategory(@RequestParam(required = true) String category) {
		return gs.getItemsByCategory(category);
	}

	@GetMapping("getGroceryItemByName")
	@ApiOperation(value = "getGroceryItemByName")
	public GroceryItem getGroceryItemByName(@RequestParam(required = true) String name) {
		return gs.getGroceryItemByName(name);
	}

	@GetMapping("showAllGroceryItems")
	@ApiOperation(value = "showAllGroceryItems")
	public List<GroceryItem> showAllGroceryItems() {
		return gs.showAllGroceryItems();
	}

}
