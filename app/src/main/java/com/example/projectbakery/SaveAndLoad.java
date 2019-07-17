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
		JSONArray jsonItems = new JSONArray();
		JSONObject[] jsonObjects = new JSONObject[masterList.size()];

		for(int i = 0; i < masterList.size(); i++)
		{
			jsonObjects[i] = createJSONItem(masterList.get(i));
		}
		for(int i = 0; i < masterList.size(); i++)
		{
			jsonItems.put(jsonObjects[i]);
		}

		return jsonItems.toString();
	}

	/**
	 * Converts an individual inventory item into a JSONObject
	 * @param item
	 * @return
	 */
	public JSONObject createJSONItem(InventoryItem item)
	{
		JSONObject jsonItem = new JSONObject();
		try
		{
			jsonItem.put("name", item.getName());
			jsonItem.put("category", item.getCategory());
			jsonItem.put("amount", item.getAmount());
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}

		return jsonItem;
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
			array = new JSONArray(jsonString);
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}

		ArrayList<JSONObject> objects = new ArrayList<>();
		for(int i = 0; i < array.length(); i++)
		{
			try
			{
				objects.add((JSONObject) array.get(i));
			}
			catch(JSONException e)
			{
				e.printStackTrace();
			}
		}

		ArrayList<InventoryItem> readInList = new ArrayList<>();
		for(int i = 0; i < objects.size(); i++)
		{
			try
			{
				readInList.add(new InventoryItem(objects.get(i).getString("name"), objects.get(i).getString("category"), objects.get(i).getInt("amount")));
			}
			catch (JSONException e)
			{
				e.printStackTrace();
			}
		}

		return readInList;
	}
}
