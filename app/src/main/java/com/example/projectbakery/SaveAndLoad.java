package com.example.projectbakery;

import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;

public class SaveAndLoad
{
	private Scanner fileInput; //Input text file stream
	private Scanner fileOutput; //Output text file stream

	public SaveAndLoad()
	{
		//TODO Set fileInput and fileOutput to the txt file for storage
	}

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
	public void saveToFile(ArrayList<InventoryItem> masterList, SharedPreferences pref) //Saves the JSON to the file
	{
		String jsonString = createJSON(masterList).toString();
		final String key = "masterStorage";

		SharedPreferences.Editor editor = pref.edit();
		editor.putString(key, jsonString);
		editor.commit();
	}
	public void loadFromFile() //Loads the JSON from the file
	{
		//TODO Create code to get JSON string from txt file and store it
	}
}
