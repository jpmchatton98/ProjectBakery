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
		//Will set the fileInput and fileOutput Scanners to the storage file
	}

	public JSONObject createJSON(ArrayList<InventoryItem> masterList) //Creates the JSON from a list
	{
		return null;
	}
	public ArrayList readJSON() //Reads the JSON from the file and returns a new list
	{
		return null;
	}
	public void saveToFile() //Saves the JSON to the file
	{

	}
	public void loadFromFile() //Loads the JSON from the file
	{

	}
}
