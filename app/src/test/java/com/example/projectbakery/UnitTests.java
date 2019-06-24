package com.example.projectbakery;

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

		InventoryItem testLiquid = new InventoryItem("frosting", "liquid", 20); //Create testing liquid item

		Assert.assertEquals("frosting", testLiquid.getName()); //Check name
		Assert.assertEquals("liquid", testLiquid.getCategory()); //Check category
		Assert.assertEquals(20, testLiquid.getAmount()); //Check amount
	}

	@Test
	public void JSONwriter()
	{
		//TODO: Test JSON writing
	}

	@Test
	public void JSONreader()
	{
		//TODO: Test JSON reading
	}

	@Test
	public void buildList() //Tests list building and sorting
	{
		InventoryItem testDough = new InventoryItem("Sourdough", "dough", 14); //Create testing dough item
		InventoryItem testLiquid = new InventoryItem ("Milk", "liquid", 7); //Create testing liquid item
		InventoryItem testEmptyDough = new InventoryItem("Chocolate Chip Dough", "dough", 0); //Create testing empty dough item

		ListBuilder builder = new ListBuilder(); //Create a ListBuilder object

		//Add items to the builder
		builder.addItem(testDough);
		builder.addItem(testLiquid);
		builder.addItem(testEmptyDough);

		ArrayList<ArrayList<InventoryItem>> builtList = builder.buildList(); //Call buildList and assign it to a list

		Assert.assertEquals(7, builtList.size()); //Check overall builtList size
		Assert.assertEquals(2, builtList.get(0).size()); //Check dough list size
		Assert.assertEquals(1, builtList.get(1).size()); //Check liquid list size

		Assert.assertEquals(testDough, builtList.get(0).get(0)); //Check first dough list item
		Assert.assertEquals(testEmptyDough, builtList.get(0).get(1)); //Check second dough list item
		Assert.assertEquals(testLiquid, builtList.get(1).get(0)); //Check first liquid list item
	}
}