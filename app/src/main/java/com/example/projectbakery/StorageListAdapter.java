package com.example.projectbakery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Custom adapter used to create list items and assign values to them with various buttons and
 * icons.
 */
public class StorageListAdapter extends ArrayAdapter<InventoryItem> implements ListAdapter
{
	class ViewHolder
	{
		TextView name;
		TextView amount;
		TextView category;
		ImageButton plusButton;
		ImageButton minusButton;
		ImageButton deleteButton;
		ImageView alert;
	}

	private ArrayList<InventoryItem> list;
	private ListBuilder builder;

	StorageListAdapter(ArrayList<InventoryItem> list, Context context, ListBuilder builder)
	{
		super(context, R.layout.list_item, list);
		this.list = list;
		this.builder = builder;
	}

	//Required functions for the custom adapter to function
	@Override
	public int getCount()
	{
		return list.size();
	}
	@Override
	public InventoryItem getItem(int position)
	{
		return list.get(position);
	}
	@Override
	public long getItemId(int position)
	{
		return 0;
	}

	public void setList(ArrayList<InventoryItem> list)
	{
		this.list = list;
	}

	/**
	 * Assigns all values to the list items and contains functions to control add, subtract, and
	 * delete buttons, as well as an alert when the item has zero quantity.
	 * @param position
	 * @param convertView
	 * @param parent
	 * @return
	 */
	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		final InventoryItem item = getItem(position); //Gets the item intended to be printed
		final ViewHolder holder; //Holder to hold all view data

		holder = new ViewHolder();

		LayoutInflater inflater = LayoutInflater.from(getContext()); //Creates the list item view
		convertView = inflater.inflate(R.layout.list_item, parent, false);

		//Get the text fields and put them in the holder
		holder.name = convertView.findViewById(R.id.itemNameText);
		holder.amount = convertView.findViewById(R.id.amountText);
		holder.category = convertView.findViewById(R.id.categoryText);

		convertView.setTag(holder);

		holder.name.setText(item.getName()); //Sets the name field to the item name

		String amountString = item.getAmount() + ""; //Sets the amount field to the item amount
		holder.amount.setText(amountString);

		//Capitalizes the first letter of the category
		String categoryString = item.getCategory();
		char[] categoryArray = categoryString.toCharArray();
		char firstLetter = Character.toUpperCase(categoryArray[0]);
		categoryArray[0] = firstLetter;
		categoryString = new String(categoryArray);

		holder.category.setText(categoryString); //Sets the category field to the capitalized item category

		//Gets the three buttons and puts them in the holder
		holder.plusButton = convertView.findViewById(R.id.addButton);
		holder.minusButton = convertView.findViewById(R.id.subtractButton);
		holder.deleteButton = convertView.findViewById(R.id.deleteButton);

		//Text input to control the amount to change by
		final TextView amountChanger = convertView.findViewById(R.id.amountChangeInput);

		holder.alert = convertView.findViewById(R.id.alert); //Alert when the item has zero quantity, invisible by default
		if(item.getAmount() > 0)
		{
			holder.alert.setVisibility(View.INVISIBLE);
		}
		else
		{
			holder.alert.setVisibility(View.VISIBLE);
		}

		//Set the plus button's onClick
		holder.plusButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				int amountToChange = 1; //Default amount to change by
				try
				{
					amountToChange = Integer.parseInt(amountChanger.getText().toString()); //Tries to parse the amount changer input
				}
				catch (NumberFormatException e){} //If the amount changer input has an invalid value

				item.setAmount(item.getAmount() + amountToChange); //Adds the amount to the item

				String amountString = item.getAmount() + "";
				holder.amount.setText(amountString); //Re-prints the amount box to show the change

				if(item.getAmount() > 0)
				{
					holder.alert.setVisibility(View.INVISIBLE); //If adding changed the item value to higher than zero
				}

				builder.saveItems(); //Save the master list to the shared preferences
			}
		});

		//Set the minus button's onClick
		holder.minusButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				if(item.getAmount() > 0) //If the item isn't already zero
				{
					int amountToChange = 1; //Default amount to change by

					try
					{
						amountToChange = Integer.parseInt(amountChanger.getText().toString()); //Tries to parse the amount changer input
					}
					catch(NumberFormatException e){} //If the amount changer input is invalid

					if(amountToChange > item.getAmount()) //If the change would reduce the item to a negative amount
					{
						item.setAmount(0); //Set it directly to zero
					}
					else
					{
						item.setAmount(item.getAmount() - amountToChange); //Reduce the quantity of the item
					}

					String amountString = item.getAmount() + "";
					holder.amount.setText(amountString); //Re-print the amount text box

					if(item.getAmount() == 0) //If the reduction reduced the item's quantity to zero
					{
						holder.alert.setVisibility(View.VISIBLE);
					}

					builder.saveItems(); //Save the master storage list to shared preferences
				}
			}
		});

		//Set the delete button's onClick
		holder.deleteButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				builder.deleteItem(item.getName()); //Deletes the current item by its name
				builder.printList(); //Re-prints the list to show changes
			}
		});

		return convertView;
	}
}
