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
	public JSONArray saveToFile(ArrayList<InventoryItem> masterList) //Saves the JSON to the file
	{
		JSONArray json = createJSON(masterList);

		return json;
	}
	public String loadFromFile() //Loads the JSON from the file
	{
		//TODO Create code to get JSON string from Firebase and store it in a string
		return "";
	}
}
