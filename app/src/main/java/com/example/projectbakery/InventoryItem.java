package com.example.projectbakery;

import java.util.Comparator;

public class InventoryItem implements Comparator<InventoryItem>
{
	private String name;
	private String category;
	private int amount;

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
