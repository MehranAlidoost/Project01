package com.testapp.oneapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.testapp.oneapp.model.AllArray;
import com.testapp.oneapp.model.ListMenuItem;

public class MainActivity extends AppCompatActivity
{

	ListView listView;
	int[]    ints;
	Intent   intent;
	AllArray listMenuItemArray;

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_main);
		getSupportActionBar ().setBackgroundDrawable (new ColorDrawable (Color.parseColor ("#494345")));

//		ListMenuItem[] menus =
//				{
//						new ListMenuItem (getString (R.string.patiensStaus), R.drawable.info),
//						new ListMenuItem (getString (R.string.setting), R.drawable.setting),
//						new ListMenuItem (getString (R.string.exit), R.drawable.exit)
//				};

		listMenuItemArray = new AllArray ("main");
		listMenuItemArray.addToListMenuItem (new ListMenuItem (getString (R.string.patiensStaus), R.drawable.info));
		listMenuItemArray.addToListMenuItem (new ListMenuItem (getString (R.string.setting), R.drawable.setting));
		listMenuItemArray.addToListMenuItem (new ListMenuItem (getString (R.string.exit), R.drawable.exit));

		listView = (ListView) findViewById (R.id.mainActivityListView);
		MainActivityListAdapter MainActivityListAdapter = new MainActivityListAdapter (MainActivity.this, listMenuItemArray);
		listView.setAdapter (MainActivityListAdapter);

		listView.setOnItemClickListener (new AdapterView.OnItemClickListener ()
		{
			@Override
			public void onItemClick (AdapterView<?> adapterView, View view, int position, long l)
			{
				switch (position)
				{
					case 0:
						intent = new Intent (MainActivity.this, PatientStatusActivity.class);
						startActivity (intent);
						break;
					case 1:
						intent = new Intent (MainActivity.this, MainSettingActivity.class);
						startActivity (intent);
						break;
					case 2:
						finish ();
				}
			}
		});
	}

}

