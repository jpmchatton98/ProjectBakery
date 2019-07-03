package com.example.projectbakery;

import android.app.Activity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class ListBuilder
{
	private ArrayList<InventoryItem> masterList; //Master, unsorted list of inventory items
	private ArrayList<ArrayList<InventoryItem>> printingList; //List of several sorted lists of inventory items for printing
	private ListView listView = null;
	private Activity mainActivity = null;

	private StorageListAdapter adapter;



	public ListBuilder()
	{
		masterList = new ArrayList<>();
		printingList = new ArrayList<>();
	}

	public ListBuilder(ListView listView, Activity mainActivity)
	{
		masterList = new ArrayList<>();
		printingList = new ArrayList<>();
		this.listView = listView;
		this.mainActivity = mainActivity;
	}

	public ArrayList getList()
	{
		return masterList;
	}
	public ArrayList getPrintingList()
	{
		return printingList;
	}

	public void setList(ArrayList<InventoryItem> masterList)
	{
		this.masterList = masterList;
	}

	public void addItem(InventoryItem item)
	{
		boolean contains = false;
		int i;
		for(i = 0; i < masterList.size(); i++)
		{
			if(masterList.get(i).getName().equals(item.getName()))
			{
				contains = true;
				break;
			}
		}

		if(contains)
		{
			InventoryItem modify = masterList.get(i);
			modify.setAmount(modify.getAmount() + item.getAmount());
			masterList.set(i, modify);
		}
		else
		{
			masterList.add(item);
		}
	}

	public void buildList() //Builds the printing list from a number of smaller lists
	{
		printingList.clear();

		//Smaller lists by category
		ArrayList<InventoryItem> doughs = new ArrayList<>();
		ArrayList<InventoryItem> liquids = new ArrayList<>();
		ArrayList<InventoryItem> breads = new ArrayList<>();
		ArrayList<InventoryItem> muffins = new ArrayList<>();
		ArrayList<InventoryItem> desserts = new ArrayList<>();
		ArrayList<InventoryItem> ingredients = new ArrayList<>();
		ArrayList<InventoryItem> miscellaneous = new ArrayList<>();

		for (int i = 0; i < masterList.size(); i++) //Fill the smaller lists
		{
			InventoryItem current = masterList.get(i);

			switch (current.getCategory())
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
				default: //This should never happen
				{
					System.out.println("How did you get here?");
					break;
				}
			}
		}

		//Sort the smaller lists
		Collections.sort(doughs, new InventoryItem());
		Collections.sort(liquids, new InventoryItem());
		Collections.sort(breads, new InventoryItem());
		Collections.sort(muffins, new InventoryItem());
		Collections.sort(desserts, new InventoryItem());
		Collections.sort(ingredients, new InventoryItem());
		Collections.sort(miscellaneous, new InventoryItem());

		//Add the smaller lists to the printing list
		printingList.add(doughs);
		printingList.add(liquids);
		printingList.add(breads);
		printingList.add(muffins);
		printingList.add(desserts);
		printingList.add(ingredients);
		printingList.add(miscellaneous);
	}

	public void printList() //Prints the printing list
	{
		buildList();

		final ArrayList<InventoryItem> singlePrintingList = new ArrayList<>();

		//For now, this prints to the console, however we need to modify this to print to the ListView later on
		for (int i = 0; i < printingList.size(); i++)
		{
			for (int j = 0; j < printingList.get(i).size(); j++)
			{
				singlePrintingList.add(printingList.get(i).get(j));
			}
		}

		mainActivity.runOnUiThread(new Runnable()
		{
			public void run()
			{
				if(adapter == null)
				{
					adapter = new StorageListAdapter(singlePrintingList, mainActivity);
					listView.setAdapter(adapter);
				}
				else
				{
					adapter.setList(singlePrintingList);

					adapter.notifyDataSetChanged();
					listView.invalidateViews();
					listView.refreshDrawableState();
				}
			}
		}
		);
	}
}
