package com.example.projectbakery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Contains functions to control JSON parsing
 */
public class SaveAndLoad
{
	/**
	 * Converts the master storage list into a JSONArray
	 * @param masterList
	 * @return
	 */
	public String createJSON(ArrayList<InventoryItem> masterList) //Creates the JSON from a list
	{
		JSONArray jsonItems = new JSONArray(); //Creates an empty JSONArray
		JSONObject[] jsonObjects = new JSONObject[masterList.size()]; //Creates an empty array of JSONObjects

		for(int i = 0; i < masterList.size(); i++) //For every item in the master list
		{
			jsonObjects[i] = createJSONItem(masterList.get(i)); //Convert to a JSONObject
		}
		for(int i = 0; i < masterList.size(); i++) //For every item in the JSONObject array
		{
			jsonItems.put(jsonObjects[i]); //Put the item in the JSONArray
		}

		return jsonItems.toString(); //Return the array as a JSON string
	}

	/**
	 * Converts an individual inventory item into a JSONObject
	 * @param item
	 * @return
	 */
	public JSONObject createJSONItem(InventoryItem item)
	{
		JSONObject jsonItem = new JSONObject(); //Creates an empty JSONObject
		try
		{
			jsonItem.put("name", item.getName()); //Puts the name in
			jsonItem.put("category", item.getCategory()); //Puts the category in
			jsonItem.put("amount", item.getAmount()); //Puts the amount in
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}

		return jsonItem; //Returns the item
	}

	/**
	 * Converts a string from the database into the master storage list
	 * @param jsonString
	 * @return
	 */
	public ArrayList readJSON(String jsonString) //Reads the JSON from a string and returns a new list
	{
		JSONArray array = null;
		try
		{
			array = new JSONArray(jsonString); //Creates a JSONArray from the string
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}

		ArrayList<JSONObject> objects = new ArrayList<>(); //Creates an empty ArrayList of JSONObjects
		for(int i = 0; i < array.length(); i++) //For the length of the JSONArray
		{
			try
			{
				objects.add((JSONObject) array.get(i)); //Add the JSONObjects to the ArrayList
			}
			catch(JSONException e)
			{
				e.printStackTrace();
			}
		}

		ArrayList<InventoryItem> readInList = new ArrayList<>(); //Creates an empty ArrayList of InventoryItem objects
		for(int i = 0; i < objects.size(); i++) //For every item in the ArrayList of JSONObjects
		{
			try
			{
				//Adds the item in the JSONObject to the InventoryItem list
				readInList.add(
						  new InventoryItem(
						  		  objects.get(i).getString("name"),
									 objects.get(i).getString("category"),
									 objects.get(i).getInt("amount")));
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
		}

		return readInList; //Returns the InventoryItem list
	}
}
