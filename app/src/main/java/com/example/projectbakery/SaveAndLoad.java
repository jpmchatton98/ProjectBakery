package com.example.projectbakery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SaveAndLoad
{
	public JSONArray createJSON(ArrayList<InventoryItem> masterList) //Creates the JSON from a list
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

		return jsonItems;
	}
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
	public ArrayList readJSON() //Reads the JSON from the file and returns a new list
	{
		//TODO Create code to change JSON string into the master storage list
		return null;
	}
	public JSONArray saveToFile(ArrayList<InventoryItem> masterList) //Saves the JSON to the file
	{
		JSONArray json = createJSON(masterList);

		return json;
	}
	public void loadFromFile() //Loads the JSON from the file
	{
		//TODO Create code to get JSON string from txt file and store it
	}
}
