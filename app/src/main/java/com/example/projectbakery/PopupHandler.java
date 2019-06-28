package com.example.projectbakery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class PopupHandler
{
	class ViewHolder
	{
		TextView amount;
		TextView name;
		Spinner category;

		Button confirm;
	}

	public void setOnClicks(final ListBuilder builder, Context context)
	{
		LayoutInflater inflater = LayoutInflater.from(context);
		View convertView = inflater.inflate(R.layout.item_input_popup, null);

		final ViewHolder holder = new ViewHolder();

		holder.amount = convertView.findViewById(R.id.itemAmount);
		holder.name = convertView.findViewById(R.id.itemName);
		holder.category = convertView.findViewById(R.id.categoryDropdown);

		holder.confirm = convertView.findViewById(R.id.confirmButton);

		holder.confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v)
			{
				String name = holder.name.getText().toString();
				String category = holder.category.getSelectedItem().toString().toLowerCase();
				int amount = Integer.parseInt(holder.amount.getText().toString());
				InventoryItem addItem = new InventoryItem(name, category, amount);

				builder.addItem(addItem);
				builder.printList();
			}
		});
	}
}
