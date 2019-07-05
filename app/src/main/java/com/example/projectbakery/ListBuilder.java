package com.example.projectbakery;

import android.app.Activity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Contains controls to build, filter, and print the master storage list in a clean and
 * organized way.
 */
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

	/**
	 * Adds an item to the list, adding the requested quantity to the current item if the item
	 * already exists in the list.
	 * @param item
	 */
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

	/**
	 * Deletes an item from the list by name.
	 * @param name
	 */
	public void deleteItem(String name)
	{
		for(int i = 0; i < masterList.size(); i++)
		{
			if(masterList.get(i).getName().equals(name))
			{
				masterList.remove(i);
				break;
			}
		}
	}

	/**
	 * Builds the list with all requested filters and searches.  Works by creating several small
	 * lists for all different categories and then assigning the necessary lists to the larger
	 * printing list that acts as a sorted and split master storage list.
	 */
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
	public void buildList(ArrayList<Boolean> filters) //Builds the printing list from a number of smaller lists with filters
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
					if(filters.get(6).booleanValue())
					{
						if(current.getAmount() <= 0)
						{
							doughs.add(current);
						}
					}
					else
					{
						doughs.add(current);
					}
					break;
				}
				case "liquid":
				{
					if(filters.get(6).booleanValue())
					{
						if(current.getAmount() <= 0)
						{
							liquids.add(current);
						}
					}
					else
					{
						liquids.add(current);
					}
					break;
				}
				case "bread":
				{
					if(filters.get(6).booleanValue())
					{
						if(current.getAmount() <= 0)
						{
							breads.add(current);
						}
					}
					else
					{
						breads.add(current);
					}
					break;
				}
				case "muffin":
				{
					if(filters.get(6).booleanValue())
					{
						if(current.getAmount() <= 0)
						{
							muffins.add(current);
						}
					}
					else
					{
						muffins.add(current);
					}
					break;
				}
				case "dessert":
				{
					if(filters.get(6).booleanValue())
					{
						if(current.getAmount() <= 0)
						{
							desserts.add(current);
						}
					}
					else
					{
						desserts.add(current);
					}
					break;
				}
				case "ingredient":
				{
					if(filters.get(6).booleanValue())
					{
						if(current.getAmount() <= 0)
						{
							ingredients.add(current);
						}
					}
					else
					{
						ingredients.add(current);
					}
					break;
				}
				case "miscellaneous":
				{
					if(filters.get(6).booleanValue())
					{
						if(current.getAmount() <= 0)
						{
							miscellaneous.add(current);
						}
					}
					else
					{
						miscellaneous.add(current);
					}
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
		if(!filters.get(0).booleanValue() && !filters.get(1).booleanValue() && !filters.get(2).booleanValue()
				  && ! filters.get(3).booleanValue() && !filters.get(4).booleanValue() && !filters.get(5).booleanValue()
				  && !filters.get(6).booleanValue())
		{
			printingList.add(doughs);
			printingList.add(liquids);
			printingList.add(breads);
			printingList.add(muffins);
			printingList.add(desserts);
			printingList.add(ingredients);
			printingList.add(miscellaneous);
		}
		else
		{
			if (filters.get(0).booleanValue())
			{
				printingList.add(doughs);
			}
			if(filters.get(1).booleanValue())
			{
				printingList.add(liquids);
			}
			if(filters.get(2).booleanValue())
			{
				printingList.add(breads);
			}
			if(filters.get(3).booleanValue())
			{
				printingList.add(muffins);
			}
			if(filters.get(4).booleanValue())
			{
				printingList.add(desserts);
			}
			if(filters.get(5).booleanValue())
			{
				printingList.add(ingredients);
			}
			if(filters.get(6).booleanValue())
			{
				printingList.add(miscellaneous);
			}
		}
	}
	public void buildList(String query) //Builds the printing list from a number of smaller lists with searching
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
					if(current.getName().contains(query))
					{
						doughs.add(current);
					}
					break;
				}
				case "liquid":
				{
					if(current.getName().contains(query))
					{
						liquids.add(current);
					}
					break;
				}
				case "bread":
				{
					if(current.getName().contains(query))
					{
						breads.add(current);
					}
					break;
				}
				case "muffin":
				{
					if(current.getName().contains(query))
					{
						muffins.add(current);
					}
					break;
				}
				case "dessert":
				{
					if(current.getName().contains(query))
					{
						desserts.add(current);
					}
					break;
				}
				case "ingredient":
				{
					if(current.getName().contains(query))
					{
						ingredients.add(current);
					}
					break;
				}
				case "miscellaneous":
				{
					if(current.getName().contains(query))
					{
						miscellaneous.add(current);
					}
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
	public void buildList(String query, ArrayList<Boolean> filters) //Builds the printing list from a number of smaller lists with searching and filters
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
					if(current.getName().contains(query))
					{
						doughs.add(current);
					}
					break;
				}
				case "liquid":
				{
					if(current.getName().contains(query))
					{
						liquids.add(current);
					}
					break;
				}
				case "bread":
				{
					if(current.getName().contains(query))
					{
						breads.add(current);
					}
					break;
				}
				case "muffin":
				{
					if(current.getName().contains(query))
					{
						muffins.add(current);
					}
					break;
				}
				case "dessert":
				{
					if(current.getName().contains(query))
					{
						desserts.add(current);
					}
					break;
				}
				case "ingredient":
				{
					if(current.getName().contains(query))
					{
						ingredients.add(current);
					}
					break;
				}
				case "miscellaneous":
				{
					if(current.getName().contains(query))
					{
						miscellaneous.add(current);
					}
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

	/**
	 * Uses a custom adapter to print the created list to the listView
	 */
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

		final ListBuilder thisThing = this;

		mainActivity.runOnUiThread(new Runnable()
		{
			public void run()
			{
				if(adapter == null)
				{
					adapter = new StorageListAdapter(singlePrintingList, mainActivity, thisThing);
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
	public void printList(ArrayList<Boolean> filters) //Prints the printing list with filters
	{
		buildList(filters);

		final ArrayList<InventoryItem> singlePrintingList = new ArrayList<>();

		//For now, this prints to the console, however we need to modify this to print to the ListView later on
		for (int i = 0; i < printingList.size(); i++)
		{
			for (int j = 0; j < printingList.get(i).size(); j++)
			{
				singlePrintingList.add(printingList.get(i).get(j));
			}
		}

		final ListBuilder thisThing = this;

		mainActivity.runOnUiThread(new Runnable()
		{
			public void run()
			{
			if(adapter == null)
			{
				adapter = new StorageListAdapter(singlePrintingList, mainActivity, thisThing);
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
	public void printList(String query) //Prints the printing list
	{
		buildList(query);

		final ArrayList<InventoryItem> singlePrintingList = new ArrayList<>();

		//For now, this prints to the console, however we need to modify this to print to the ListView later on
		for (int i = 0; i < printingList.size(); i++)
		{
			for (int j = 0; j < printingList.get(i).size(); j++)
			{
				singlePrintingList.add(printingList.get(i).get(j));
			}
		}

		final ListBuilder thisThing = this;

		mainActivity.runOnUiThread(new Runnable()
			{
				public void run()
				{
				if(adapter == null)
				{
					adapter = new StorageListAdapter(singlePrintingList, mainActivity, thisThing);
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
