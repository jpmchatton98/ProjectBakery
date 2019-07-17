package com.example.projectbakery;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

	SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		getSupportActionBar().hide();

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final TextView searchBox = findViewById(R.id.searchBox);

		searchBox.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{
				//Do Nothing
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
				query = searchBox.getText().toString();
				searchItems();
			}
			@Override
			public void afterTextChanged(Editable s)
			{
				//Do Nothing
			}
		});

		builder = new ListBuilder((ListView) findViewById(R.id.itemList), this);

		boolean testing = false;
		if(testing)
		{
			builder.addItem(new InventoryItem("Non-Empty Dough", "dough", 60));
			builder.addItem(new InventoryItem("Empty Dough", "dough", 0));
			builder.addItem(new InventoryItem("Non-Empty Liquid", "liquid", 54));
			builder.addItem(new InventoryItem("Empty Item", "muffin", 0));

			boolean tonsOfItems = false;
			if(tonsOfItems)
			{
				for (int i = 0; i < 300; i++)
				{
					builder.addItem(new InventoryItem("Item #" + i, "ingredient", 5));
				}
			}
		}

		saveAndLoad = new SaveAndLoad();
		preferences = getPreferences(Context.MODE_PRIVATE);

		JSONArray defaultArray = new JSONArray();

		builder.setList(saveAndLoad.readJSON(preferences.getString("masterStorage", defaultArray.toString())));
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

	 	final PopupWindow window = new PopupWindow(popupView, width, height, true);

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
				if(filtered)
				{
					builder.setFilters(filters);
				}
				if(!query.equals(""))
				{
					builder.setQuery(query);
				}

				builder.printList();

				window.dismiss();
			}
		});
	}


		/**
		 * Takes in a query string from the search box and filters the listView appropriately
		 */
		public void searchItems()
		{
			builder.setQuery(query);
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
		if(filtered)
		{
			builder.setFilters(filters);
		}
		builder.printList();
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

		final PopupWindow window = new PopupWindow(popupView, width, height, false);

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

				builder.setFilters(filters);

				if(!query.equals(""))
				{
					builder.setQuery(query);
				}

				builder.printList();

				window.dismiss();
			}
		});
	}
}