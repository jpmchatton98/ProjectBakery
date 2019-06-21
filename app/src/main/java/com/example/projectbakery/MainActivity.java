package com.example.projectbakery;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import java.util.ArrayList;

import static junit.framework.Assert.*; //Useless now, wil be used for unit testing later

public class MainActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
	}
}