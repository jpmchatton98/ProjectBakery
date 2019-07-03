package com.example.projectbakery;

import android.app.Activity;
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

	private ArrayList<InventoryItem> list = null;
	private Context context;
	private ListBuilder builder = null;

	StorageListAdapter(ArrayList<InventoryItem> list, Context context, ListBuilder builder)
	{
		super(context, R.layout.list_item, list);
		this.list = list;
		this.context = context;
		this.builder = builder;
	}

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

	@Override
	public View getView(final int position, View convertView, ViewGroup parent)
	{
		final InventoryItem item = getItem(position);
		final ViewHolder holder;

		holder = new ViewHolder();

		LayoutInflater inflater = LayoutInflater.from(getContext());
		convertView = inflater.inflate(R.layout.list_item, parent, false);

		holder.name = convertView.findViewById(R.id.itemNameText);
		holder.amount = convertView.findViewById(R.id.amountText);
		holder.category = convertView.findViewById(R.id.categoryText);

		convertView.setTag(holder);

		holder.name.setText(item.getName());

		String amountString = item.getAmount() + "";
		holder.amount.setText(amountString);

		String categoryString = item.getCategory();
		char[] categoryArray = categoryString.toCharArray();
		char firstLetter = Character.toUpperCase(categoryArray[0]);
		categoryArray[0] = firstLetter;
		categoryString = new String(categoryArray);

		holder.category.setText(categoryString);

		holder.plusButton = convertView.findViewById(R.id.addButton);
		holder.minusButton = convertView.findViewById(R.id.subtractButton);
		holder.deleteButton = convertView.findViewById(R.id.deleteButton);

		final TextView amountChanger = convertView.findViewById(R.id.amountChangeInput);

		holder.alert = convertView.findViewById(R.id.alert);
		if(item.getAmount() > 0)
		{
			holder.alert.setVisibility(View.INVISIBLE);
		}
		else
		{
			holder.alert.setVisibility(View.VISIBLE);
		}

		holder.plusButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				int amountToChange = 1;
				try
				{
					amountToChange = Integer.parseInt(amountChanger.getText().toString());
				}
				catch (NumberFormatException e){}

				item.setAmount(item.getAmount() + amountToChange);

				String amountString = item.getAmount() + "";
				holder.amount.setText(amountString);

				if(item.getAmount() > 0)
				{
					holder.alert.setVisibility(View.INVISIBLE);
				}
			}
		});
		holder.minusButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				if(item.getAmount() > 0)
				{
					int amountToChange = 1;

					try
					{
						amountToChange = Integer.parseInt(amountChanger.getText().toString());
					}
					catch(NumberFormatException e){}

					if(amountToChange > item.getAmount())
					{
						item.setAmount(0);
					}
					else
					{
						item.setAmount(item.getAmount() - amountToChange);
					}

					String amountString = item.getAmount() + "";
					holder.amount.setText(amountString);

					if(item.getAmount() == 0)
					{
						holder.alert.setVisibility(View.VISIBLE);
					}
				}
			}
		});
		holder.deleteButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				builder.deleteItem(item.getName());
				builder.printList();
			}
		});

		return convertView;
	}
}
