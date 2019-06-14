package com.example.projectbakery;

import java.util.Comparator;

public class InventoryItem implements Comparator<InventoryItem>
{
	private String name; //Actual name of the inventory item
	private String category; //Category of the inventory item (dough, liquid, etc.)
	private int amount; //Amount of the inventory item

	public InventoryItem()
	{
		name = "";
		category = "";
		amount = -1;
	}
	public InventoryItem(String name, String category, int amount)
	{
		this.name = name;
		this.category = category;
		this.amount = amount;
	}

	public String getName()
	{
		return name;
	}
	public String getCategory()
	{
		return category;
	}
	public int getAmount()
	{
		return amount;
	}

	//We only need setters for name and amount because category will work with
	//radio buttons and not a text entry field
	public void setName(String name)
	{
		this.name = name;
	}
	public void setAmount(int amount)
	{
		this.amount = amount;
	}

	@Override
	public String toString()
	{
		return "";
	}
	//Compares by first putting any items with a zero quantity at the end and then sorting
	//by alphabetical order with the name
	@Override
	public int compare(InventoryItem item1, InventoryItem item2)
	{
		if(item1.getAmount() == 0)
		{
			return 1;
		}
		else if(item2.getAmount() == 0)
		{
			return -1;
		}
		else
		{
			return item1.getName().compareTo(item2.getName());
		}
	}
}
