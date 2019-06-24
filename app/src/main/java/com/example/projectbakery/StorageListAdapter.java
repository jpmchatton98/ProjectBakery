package com.example.projectbakery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
	}

	private ArrayList<InventoryItem> list = null;
	private Context context;

	StorageListAdapter(ArrayList<InventoryItem> list, Context context)
	{
		super(context, R.layout.list_item, list);
		this.list = list;
		this.context = context;
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		InventoryItem item = getItem(position);

		ViewHolder holder;

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
		categoryString = categoryArray.toString();

		holder.category.setText(categoryString);

		return convertView;
	}
}
