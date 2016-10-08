package com.testapp.oneapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class AboutActivity extends AppCompatActivity
{

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_about);
		getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
		getSupportActionBar ().setTitle (getString (R.string.en_about));
	}

	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{
		this.finish ();
		return true;
	}
}
