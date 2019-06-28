package com.example.projectbakery;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
	ListBuilder builder = null;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		builder = new ListBuilder((ListView) findViewById(R.id.itemList), this);
		builder.addItem(new InventoryItem("A", "dough", 60));
		builder.addItem(new InventoryItem("B", "liquid", 54));

		builder.printList();
	}

	public void saveStorage(View view)
	{
		SaveAndLoad saving = new SaveAndLoad();

		ArrayList<InventoryItem> testItems = new ArrayList<>();
		testItems.add(new InventoryItem("A", "A", 5));
		testItems.add(new InventoryItem("B", "A", 7));
		testItems.add(new InventoryItem("C", "B", 4));

		Context context = this;
		SharedPreferences pref = context.getSharedPreferences("com.example.projectbakery", Context.MODE_PRIVATE);
		saving.saveToFile(testItems, pref);
	}

	public void loadStorage(View view)
	{
		Context context = this;
		SharedPreferences pref = context.getSharedPreferences("com.example.projectbakery", Context.MODE_PRIVATE);

		System.out.println(pref.getString("masterStorage", ""));
	}

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
				int itemAmount = Integer.parseInt(amountInput.getText().toString());
				String itemCategory = categoryDropDown.getSelectedItem().toString().toLowerCase();

				InventoryItem item = new InventoryItem(itemName, itemCategory, itemAmount);
				builder.addItem(item);
				builder.printList();
			}
		});
	}
	public void searchItems(View view)
	{
		TextView searchBox = findViewById(R.id.searchBox);
		String query = searchBox.getText().toString();
		//TODO Assign query String to the list to search by item name
	}
	public void filterItems(View view)
	{
		//TODO Create dropdown window with radio buttons for filters and assign the filters to the list
	}
}