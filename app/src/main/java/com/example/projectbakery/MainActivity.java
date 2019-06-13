package com.example.projectbakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import static junit.framework.Assert.*;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void assertTest()
    {
        InventoryItem testItem = new InventoryItem("Regular Dogh", "dough", 7);

        assertEquals("Regular Dogh", testItem.getName());
        assertEquals("dough", testItem.getCategory());
        assertEquals(7, testItem.getAmount());

        testItem.setName("Regular Dough");

        assertEquals("Regular Dough", testItem.getName());

        ArrayList<InventoryItem> testList = new ArrayList<>();
        testList.add(testItem);

        assertEquals(testList.get(0), testItem);

        testList.remove(0);

        assertEquals(0, testList.size());
    }
}