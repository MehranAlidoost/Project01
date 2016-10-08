package com.testapp.oneapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainSettingActivity extends AppCompatActivity
{

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_main_setting);
		getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
		getSupportActionBar ().setTitle (getString (R.string.en_setting));
	}

	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{
		this.finish ();
		return true;
	}
}
