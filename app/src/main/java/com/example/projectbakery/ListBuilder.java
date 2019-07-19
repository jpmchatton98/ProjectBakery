package com.example.projectbakery;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.ListView;
import android.widget.Toast;

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

	private String query = "";
	private ArrayList<Boolean> filters = null;

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

	public ArrayList getPrintingList()
	{
		return printingList;
	}

	public void setFilters(ArrayList<Boolean> filters)
	{
		this.filters = filters;
	}
	public void setQuery(String query)
	{
		this.query = query;
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
		boolean contains = false; //If the list already contains the item
		int i; //External variable so we can use it later
		//Check to see if the list contains the item
		for(i = 0; i < masterList.size(); i++)
		{
			if(masterList.get(i).getName().equals(item.getName()))
			{
				contains = true;
				break;
			}
		}

		if(contains) //If the list contains the item
		{
			//Get the item and increase the quantity
			InventoryItem modify = masterList.get(i);
			modify.setAmount(modify.getAmount() + item.getAmount());
			masterList.set(i, modify);

			//Print a toast to notify the user
			final String toastString = modify.getName() + " already exists, " + item.getAmount() + " added to item.";

			mainActivity.runOnUiThread(new Runnable(){
				@Override
				public void run()
				{
					Toast.makeText(mainActivity, toastString, Toast.LENGTH_LONG).show();
				}
			});
		}
		else
		{
			masterList.add(item); //Just add the item
		}
		saveItems(); //Save the list
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
				saveItems();
				break;
			}
		}
	}

	/**
	 * Builds the list without any filters or searches.  Works by creating several small
	 * lists for all different categories and then assigning the necessary lists to the larger
	 * printing list that acts as a sorted and split master storage list.
	 */
	public void buildList()
	{
		printingList.clear(); //Clears the current printingList

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

	/**
	 * Modified version of the buildList() function that builds the list while taking into
	 * account all user-requested filters
	 * @param filters
	 */
	public void buildList(ArrayList<Boolean> filters)
	{
		printingList.clear(); //Clears the current printingList

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
					if(filters.get(7).booleanValue()) //If the out filter is active
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
					if(filters.get(7).booleanValue()) //If the out filter is active
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
					if(filters.get(7).booleanValue()) //If the out filter is active
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
					if(filters.get(7).booleanValue()) //If the out filter is active
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
					if(filters.get(7).booleanValue()) //If the out filter is active
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
					if(filters.get(7).booleanValue()) //If the out filter is active
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
					if(filters.get(7).booleanValue()) //If the out filter is active
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
				  && !filters.get(6).booleanValue()) //If no filters are active, treat as if all are active
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
			//Only adds the filtered lists
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

	/**
	 * Modified version of the buildList() function that builds the list while
	 * taking into account a user-requested search query
	 * @param query
	 */
	public void buildList(String query)
	{
		printingList.clear(); //Clears the current printingList

		//Smaller lists by category
		ArrayList<InventoryItem> doughs = new ArrayList<>();
		ArrayList<InventoryItem> liquids = new ArrayList<>();
		ArrayList<InventoryItem> breads = new ArrayList<>();
		ArrayList<InventoryItem> muffins = new ArrayList<>();
		ArrayList<InventoryItem> desserts = new ArrayList<>();
		ArrayList<InventoryItem> ingredients = new ArrayList<>();
		ArrayList<InventoryItem> miscellaneous = new ArrayList<>();

		query = query.toUpperCase(); //Makes sure case doesn't matter for the query

		for (int i = 0; i < masterList.size(); i++) //Fill the smaller lists
		{
			InventoryItem current = masterList.get(i);

			switch (current.getCategory())
			{
				case "dough":
				{
					if((current.getName().toUpperCase()).contains(query)) //If the item name contains the query
					{
						doughs.add(current);
					}
					break;
				}
				case "liquid":
				{
					if((current.getName().toUpperCase()).contains(query)) //If the item name contains the query
					{
						liquids.add(current);
					}
					break;
				}
				case "bread":
				{
					if((current.getName().toUpperCase()).contains(query)) //If the item name contains the query
					{
						breads.add(current);
					}
					break;
				}
				case "muffin":
				{
					if((current.getName().toUpperCase()).contains(query)) //If the item name contains the query
					{
						muffins.add(current);
					}
					break;
				}
				case "dessert":
				{
					if((current.getName().toUpperCase()).contains(query)) //If the item name contains the query
					{
						desserts.add(current);
					}
					break;
				}
				case "ingredient":
				{
					if((current.getName().toUpperCase()).contains(query)) //If the item name contains the query
					{
						ingredients.add(current);
					}
					break;
				}
				case "miscellaneous":
				{
					if((current.getName().toUpperCase()).contains(query)) //If the item name contains the query
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
	 * Modified version of the buildList() function that builds the list while taking into
	 * account all user-requested filters and a user-requested search query
	 * @param query
	 * @param filters
	 */
	public void buildList(String query, ArrayList<Boolean> filters)
	{
		printingList.clear(); //Clears the current printingList

		//Smaller lists by category
		ArrayList<InventoryItem> doughs = new ArrayList<>();
		ArrayList<InventoryItem> liquids = new ArrayList<>();
		ArrayList<InventoryItem> breads = new ArrayList<>();
		ArrayList<InventoryItem> muffins = new ArrayList<>();
		ArrayList<InventoryItem> desserts = new ArrayList<>();
		ArrayList<InventoryItem> ingredients = new ArrayList<>();
		ArrayList<InventoryItem> miscellaneous = new ArrayList<>();

		query = query.toUpperCase(); //Makes sure that case doesn't matter on the query

		for (int i = 0; i < masterList.size(); i++) //Fill the smaller lists
		{
			InventoryItem current = masterList.get(i);

			switch (current.getCategory())
			{
				case "dough":
				{
					if((current.getName().toUpperCase()).contains(query)) //If the item name contains the query
					{
						if(filters.get(7).booleanValue()) //If the out filter is active
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
					}
					break;
				}
				case "liquid":
				{
					if((current.getName().toUpperCase()).contains(query)) //If the item name contains the query
					{
						if(filters.get(7).booleanValue()) //If the out filter is active
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
					}
					break;
				}
				case "bread":
				{
					if((current.getName().toUpperCase()).contains(query)) //If the item name contains the query
					{
						if(filters.get(7).booleanValue()) //If the out filter is active
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
					}
					break;
				}
				case "muffin":
				{
					if((current.getName().toUpperCase()).contains(query)) //If the item name contains the query
					{
						if(filters.get(7).booleanValue()) //If the out filter is active
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
					}
					break;
				}
				case "dessert":
				{
					if((current.getName().toUpperCase()).contains(query)) //If the item name contains the query
					{
						if(filters.get(7).booleanValue()) //If the out filter is active
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
					}
					break;
				}
				case "ingredient":
				{
					if((current.getName().toUpperCase()).contains(query)) //If the item name contains the query
					{
						if(filters.get(7).booleanValue()) //If the out filter is active
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
					}
					break;
				}
				case "miscellaneous":
				{
					if((current.getName().toUpperCase()).contains(query)) //If the item name contains the query
					{
						if(filters.get(7).booleanValue()) //If the out filter is active
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
				  && !filters.get(6).booleanValue()) //If no filters are active, treat as if all filters are active
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
			//Only add filtered lists
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

	/**
	 * Uses a custom adapter to print the created list to the listView
	 */
	public void printList() //Prints the printing list
	{
		boolean filtered = false;
		boolean searched = false;

		//Checks if the filters are active
		if(filters != null)
		{
			for (int i = 0; i < filters.size(); i++)
			{
				if (filters.get(i).booleanValue())
				{
					filtered = true;
					break;
				}
			}
		}
		//Checks if the search is active
		if (query != "")
		{
			searched = true;
		}

		//Builds the list with the appropriate function
		if(!filtered && !searched)
		{
			buildList();
		}
		else if(filtered && !searched)
		{
			buildList(filters);
		}
		else if(!filtered && searched)
		{
			buildList(query);
		}
		else
		{
			buildList(query, filters);
		}

		//Creates an empty ArrayList
		final ArrayList<InventoryItem> singlePrintingList = new ArrayList<>();

		//Compiles the printingList down to a single list
		for (int i = 0; i < printingList.size(); i++)
		{
			for (int j = 0; j < printingList.get(i).size(); j++)
			{
				singlePrintingList.add(printingList.get(i).get(j));
			}
		}

		final ListBuilder thisThing = this; //Gets a final version of this

		mainActivity.runOnUiThread(new Runnable()
		{
			public void run()
			{
				if(adapter == null) //If the list hasn't been printed yet
				{
					adapter = new StorageListAdapter(singlePrintingList, mainActivity, thisThing); //Sets the adapter up
					listView.setAdapter(adapter);
				}
				else //If the list has previously been printed
				{
					adapter.setList(singlePrintingList); //Modifies the adapter

					//Notifies the adapter of the changes
					adapter.notifyDataSetChanged();
					listView.invalidateViews();
					listView.refreshDrawableState();
				}
			}
		}
		);
	}

	/**
	 * Saves the master storage list to the shared preferences.  This function gets called
	 * wherever the list changes.
	 */
	public void saveItems()
	{
		SharedPreferences preferences;
		SharedPreferences.Editor editor;

		//Gets the preferences and their editor
		preferences = mainActivity.getPreferences(Context.MODE_PRIVATE);
		editor = preferences.edit();

		//Puts a JSON string into the shared preferences
		editor.putString("masterStorage", new SaveAndLoad().createJSON(masterList));
		editor.commit();
	}
}
