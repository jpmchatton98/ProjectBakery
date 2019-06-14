package com.example.projectbakery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static junit.framework.Assert.*; //Useless now, wil be used for unit testing later

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}