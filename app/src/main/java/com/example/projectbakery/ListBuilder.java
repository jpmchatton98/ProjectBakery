package com.example.projectbakery;

import java.util.ArrayList;

public class ListBuilder
{
	private ArrayList<InventoryItem> masterList;

	public ArrayList getList()
	{
		return masterList;
	}
	public void buildList()
	{

	}
	public void printList()
	{
		for(int i = 0; i < masterList.size(); i++)
		{
			System.out.println(masterList.get(i).toString());
		}
	}
}
