package com.example.projectbakery;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class ListBuilder
{
	private ArrayList<InventoryItem> masterList;
	private ArrayList<ArrayList<InventoryItem>> printingList;

	ListBuilder()
	{
		masterList = new ArrayList<>();
		printingList = new ArrayList<>();
	}

	public ArrayList getList()
	{
		return masterList;
	}
	public void addItem(InventoryItem item)
	{
		masterList.add(item);
	}

	public void buildList()
	{
		ArrayList<InventoryItem> doughs = new ArrayList<>();
		ArrayList<InventoryItem> liquids = new ArrayList<>();
		ArrayList<InventoryItem> breads = new ArrayList<>();
		ArrayList<InventoryItem> muffins = new ArrayList<>();
		ArrayList<InventoryItem> desserts = new ArrayList<>();
		ArrayList<InventoryItem> ingredients = new ArrayList<>();
		ArrayList<InventoryItem> miscellaneous = new ArrayList<>();

		for(int i = 0; i < masterList.size(); i++)
		{
			InventoryItem current = masterList.get(i);

			switch(current.getCategory())
			{
				case "dough":
				{
					doughs.add(current);
					break;
				}
				case "liquid":
				{
					liquids.add(current);
					break;
				}
				case "bread":
				{
					breads.add(current);
					break;
				}
				case "muffin":
				{
					muffins.add(current);
					break;
				}
				case "dessert":
				{
					desserts.add(current);
					break;
				}
				case "ingredient":
				{
					ingredients.add(current);
					break;
				}
				case "miscellaneous":
				{
					miscellaneous.add(current);
					break;
				}
				default:
				{
					System.out.println("How did you get here?");
					break;
				}
			}
		}

		Collections.sort(doughs, new InventoryItem());
		Collections.sort(liquids, new InventoryItem());
		Collections.sort(breads, new InventoryItem());
		Collections.sort(muffins, new InventoryItem());
		Collections.sort(desserts, new InventoryItem());
		Collections.sort(ingredients, new InventoryItem());
		Collections.sort(miscellaneous, new InventoryItem());

		printingList.add(doughs);
		printingList.add(liquids);
		printingList.add(breads);
		printingList.add(muffins);
		printingList.add(desserts);
		printingList.add(ingredients);
		printingList.add(miscellaneous);
	}
	public void printList()
	{
		for(int i = 0; i < printingList.size(); i++)
		{
			for(int j = 0; j < printingList.get(i).size(); j++)
			{
				System.out.println(printingList.get(i).get(j).toString());
			}
		}
	}
}
