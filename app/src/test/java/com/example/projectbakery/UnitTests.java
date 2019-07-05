package com.example.projectbakery;

import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class UnitTests
{
	@Test
	public void inventoryItemTest() //Tests InventoryItem creation
	{
		InventoryItem testDough = new InventoryItem("sourdough", "dough", 10); //Create testing dough item

		Assert.assertEquals("sourdough", testDough.getName()); //Check name
		Assert.assertEquals("dough", testDough.getCategory()); //Check category
		Assert.assertEquals(10, testDough.getAmount()); //Check amount
		//Test changing test dough quantity
		testDough.setAmount(12);
		Assert.assertEquals(12, testDough.getAmount()); //Check amount

		//Test adding one to test dough
		testDough.setAmount(testDough.getAmount() + 1);
		Assert.assertEquals(13, testDough.getAmount()); //Check amount

		//Test subtracting one to test dough
		testDough.setAmount(testDough.getAmount() - 1);
		Assert.assertEquals(12, testDough.getAmount()); //Check amount


		InventoryItem testLiquid = new InventoryItem("frosting", "liquid", 20); //Create testing liquid item

		Assert.assertEquals("frosting", testLiquid.getName()); //Check name
		Assert.assertEquals("liquid", testLiquid.getCategory()); //Check category
		Assert.assertEquals(20, testLiquid.getAmount()); //Check amount

		//Test changing test liquid quantity
		testDough.setAmount(14);
		Assert.assertEquals(14, testDough.getAmount()); //Check amount

		//Test adding one to test liquid
		testDough.setAmount(testLiquid.getAmount() + 1);
		Assert.assertEquals(15, testLiquid.getAmount()); //Check amount

		//Test subtracting one to test liquid
		testDough.setAmount(testLiquid.getAmount() - 1);
		Assert.assertEquals(14, testDough.getAmount()); //Check amount

	}

	@Test
	public void JSONwriter()
	{
		SaveAndLoad saver = new SaveAndLoad(); //SaveAndLoad for JSON creation

		//TODO: Test JSON writing: Dale

	}

	@Test
	public void JSONreader()
	{
		ArrayList<InventoryItem> testItems = new ArrayList<>();
		testItems.add(new InventoryItem("Sourdough", "dough", 14));
		testItems.add(new InventoryItem("Milk", "liquid", 7));
		testItems.add(new InventoryItem("Chocolate Chip Dough", "dough", 4));
		testItems.add(new InventoryItem("Kiaser Buns","Carter",121));

		SaveAndLoad save = new SaveAndLoad();

		JSONArray inventoryList = save.createJSON(testItems);

		String json = inventoryList.toString();

		String jsonTemplate = "[{\"amount\":14,\"name\":\"Sourdough\",\"category\":\"dough\"},{\"amount\":7,\"name\":\"Milk\",\"category\":\"liquid\"},{\"amount\":4,\"name\":\"Chocolate Chip Dough\",\"category\":\"dough\"},{\"amount\":121,\"name\":\"Kiaser Buns\",\"category\":\"Carter\"}]";

		Assert.assertEquals(jsonTemplate , json);

		ArrayList<InventoryItem> readTest = save.readJSON(json);

		Assert.assertEquals(testItems.toString(), readTest.toString());
	}

	@Test
	public void buildList() //Tests list building and sorting
	{
		InventoryItem testDough = new InventoryItem("Sourdough", "dough", 14); //Create testing dough item
		InventoryItem testLiquid = new InventoryItem ("Milk", "liquid", 7); //Create testing liquid item
		InventoryItem testEmptyDough = new InventoryItem("Chocolate Chip Dough", "dough", 0); //Create testing empty dough item

		ListBuilder builder = new ListBuilder(); //Create a ListBuilder object

		//Add items to the builder
		builder.addItem(testEmptyDough);
		builder.addItem(testDough);
		builder.addItem(testLiquid);

		builder.buildList();

		ArrayList<ArrayList<InventoryItem>> builtList = builder.getPrintingList(); //Call buildList and assign it to a list

		Assert.assertEquals(7, builtList.size()); //Check overall builtList size
		Assert.assertEquals(2, builtList.get(0).size()); //Check dough list size
		Assert.assertEquals(1, builtList.get(1).size()); //Check liquid list size

		Assert.assertEquals(testDough, builtList.get(0).get(0)); //Check first dough list item
		Assert.assertEquals(testEmptyDough, builtList.get(0).get(1)); //Check second dough list item
		Assert.assertEquals(testLiquid, builtList.get(1).get(0)); //Check first liquid list item
	}
}