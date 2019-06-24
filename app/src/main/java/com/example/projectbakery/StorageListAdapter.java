package com.example.projectbakery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StorageListAdapter extends BaseAdapter implements ListAdapter
{
	private ArrayList<InventoryItem> list = null;
	private Context context;

	StorageListAdapter(ArrayList<InventoryItem> list, Context context)
	{
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount()
	{
		return list.size();
	}

	@Override
	public Object getItem(int position)
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
		View view = convertView;
		if(view == null)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.list_item, null);
		}

		TextView name = (TextView) view.findViewById(R.id.nameLabel);
		TextView category = (TextView) view.findViewById(R.id.categoryLabel);
		TextView amount = (TextView) view.findViewById(R.id.amountLabel);

		return view;
	}
}
