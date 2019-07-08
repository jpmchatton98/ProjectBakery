package com.example.projectbakery;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.*;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Primary UI Activity
 */
public class MainActivity extends AppCompatActivity
{
	ListBuilder builder = null;
	SaveAndLoad saveAndLoad = null;

	/**
	 * filters contains several booleans to control the various filters possible in the app
	 * 0 - Doughs
	 * 1 - Liquids
	 * 2 - Breads
	 * 3 - Muffins
	 * 4 - Desserts
	 * 5 - Ingredients
	 * 6 - Miscellaneous
	 * 7 - Out (Zero Quantity)
	 */
	ArrayList<Boolean> filters = null;
	String query = "";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		getSupportActionBar().hide();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		builder = new ListBuilder((ListView) findViewById(R.id.itemList), this);
		builder.addItem(new InventoryItem("Non-Empty Dough", "dough", 60));
		builder.addItem(new InventoryItem("Empty Dough", "dough", 0));
		builder.addItem(new InventoryItem("Non-Empty Liquid", "liquid", 54));
		builder.addItem(new InventoryItem("Empty Item", "muffin", 0));

		builder.printList();

		saveAndLoad = new SaveAndLoad();
	}

	/**
	 * Creates a JSONArray and saves it to the Firebase database
	 */
	public void saveStorage()
	{
		JSONArray save = saveAndLoad.createJSON(builder.getList());
		//TODO create code to put save in the Firebase database
	}

	/**
	 * Grabs the text from the Firebase database and parses it into a JSONArray which is then parsed
	 * into the master storage list
	 */
	public void loadStorage()
	{
		String load = "";
		//TODO create code to get load from the Firebase database

		builder.setList(saveAndLoad.readJSON(load));
		builder.printList();
	}

	/**
	 * Creates the pop-up window for adding items to the master storage list and handles the actual
	 * adding of the items
	 * @param view
	 */
	public void addItem(View view)
	{
	 	LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	 	View popupView = inflater.inflate(R.layout.item_input_popup, null);

	 	int width = LinearLayout.LayoutParams.WRAP_CONTENT;
	 	int height = LinearLayout.LayoutParams.WRAP_CONTENT;

	 	boolean focusable = true;
	 	final PopupWindow window = new PopupWindow(popupView, width, height, focusable);

	 	window.showAtLocation(view, Gravity.CENTER, 0, 0);

	 	View popUpView = window.getContentView();
	 	final TextView nameInput = popUpView.findViewById(R.id.itemName);
	 	final TextView amountInput = popUpView.findViewById(R.id.itemAmount);
	 	final Spinner categoryDropDown = popUpView.findViewById(R.id.categoryDropdown);

	 	Button confirmButton = popUpView.findViewById(R.id.confirmButton);
	 	confirmButton.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v)
			{
				String itemName = nameInput.getText().toString();
				int itemAmount = 0;
				try
				{
					itemAmount = Integer.parseInt(amountInput.getText().toString());
				}
				catch(NumberFormatException e)
				{
					e.printStackTrace();
				}
				String itemCategory = categoryDropDown.getSelectedItem().toString().toLowerCase();

				InventoryItem item = new InventoryItem(itemName, itemCategory, itemAmount);
				builder.addItem(item);

				boolean filtered = false;
				boolean searched = false;

				if(filters != null)
				{
					for (int i = 0; i < filters.size(); i++)
					{
						if (filters.get(i).booleanValue())
						{
							filtered = true;
							break;
						}
					}
				}
				if (query != "")
				{
					searched = true;
				}

				if(!filtered && !searched)
				{
					builder.printList();
				}
				else if(filtered && !searched)
				{
					builder.printList(filters);
				}
				else if(!filtered && searched)
				{
					builder.printList(query);
				}
				else
				{
					builder.printList(query, filters);
				}

				window.dismiss();
			}
		});
	}

	/**
	 * Takes in a query string from the search box and filters the listView appropriately
	 * @param view
	 */
	public void searchItems(View view)
	{
		TextView searchBox = findViewById(R.id.searchBox);
		query = searchBox.getText().toString();

		boolean filtered = false;
		if(filters != null)
		{
			for (int i = 0; i < filters.size(); i++)
			{
				if (filters.get(i).booleanValue())
				{
					filtered = true;
					break;
				}
			}
		}
		if(!filtered)
		{
			builder.printList(query);
		}
		else
		{
			builder.printList(query, filters);
		}
	}

	/**
	 * Creates mini pop-up window to allow user to select filters and then filters the listView
	 * appropriately
	 * @param view
	 */
	public void filterItems(View view)
	{
		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View popupView = inflater.inflate(R.layout.filter_window, null);

		int width = LinearLayout.LayoutParams.WRAP_CONTENT;
		int height = LinearLayout.LayoutParams.WRAP_CONTENT;

		boolean focusable = true;
		final PopupWindow window = new PopupWindow(popupView, width, height, focusable);

		window.showAtLocation(view, Gravity.CENTER, 0, 0);

		View popUpView = window.getContentView();
		final Switch doughsFilter = popUpView.findViewById(R.id.doughsFilter);
		final Switch liquidsFilter = popUpView.findViewById(R.id.liquidsFilter);
		final Switch breadsFilter = popUpView.findViewById(R.id.breadsFilter);
		final Switch muffinsFilter = popUpView.findViewById(R.id.muffinsFilter);
		final Switch dessertsFilter = popUpView.findViewById(R.id.dessertsFilter);
		final Switch ingredientsFilter = popUpView.findViewById(R.id.ingredientsFilter);
		final Switch miscellaneousFilter = popUpView.findViewById(R.id.miscellaneousFilter);
		final Switch outFilter = popUpView.findViewById(R.id.outFilter);

		final Button confirmButton = popUpView.findViewById(R.id.filterConfirmButton);

		if(filters != null)
		{
			if(filters.size() != 0)
			{
				doughsFilter.setChecked(filters.get(0));
				liquidsFilter.setChecked(filters.get(1));
				breadsFilter.setChecked(filters.get(2));
				muffinsFilter.setChecked(filters.get(3));
				dessertsFilter.setChecked(filters.get(4));
				ingredientsFilter.setChecked(filters.get(5));
				miscellaneousFilter.setChecked(filters.get(6));
				outFilter.setChecked(filters.get(7));

				filters.clear();
			}
		}
		else
		{
			filters = new ArrayList<>();
		}

		confirmButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v)
			{
				filters.add(doughsFilter.isChecked());
				filters.add(liquidsFilter.isChecked());
				filters.add(breadsFilter.isChecked());
				filters.add(muffinsFilter.isChecked());
				filters.add(dessertsFilter.isChecked());
				filters.add(ingredientsFilter.isChecked());
				filters.add(miscellaneousFilter.isChecked());

				filters.add(outFilter.isChecked());

				if(query.equals(""))
				{
					builder.printList(filters);
				}
				else
				{
					builder.printList(query, filters);
				}

				window.dismiss();
			}
		});
	}
}