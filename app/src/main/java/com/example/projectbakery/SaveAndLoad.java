package com.example.projectbakery;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;

public class SaveAndLoad
{
	private Scanner fileInput; //Input text file stream
	private Scanner fileOutput; //Output text file stream
	private JSONObject json; //JSON object for storage list

	public SaveAndLoad()
	{
		//TODO Set fileInput and fileOutput to the txt file for storage
	}

	public JSONObject createJSON(ArrayList<InventoryItem> masterList) //Creates the JSON from a list
	{
		//TODO Create code to create JSON string from the master storage list
		return null;
	}
	public ArrayList readJSON() //Reads the JSON from the file and returns a new list
	{
		//TODO Create code to change JSON string into the master storage list
		return null;
	}
	public void saveToFile() //Saves the JSON to the file
	{
		//TODO Create code to save JSON string to txt file
	}
	public void loadFromFile() //Loads the JSON from the file
	{
		//TODO Create code to get JSON string from txt file and store it
	}
}
