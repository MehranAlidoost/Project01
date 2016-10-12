package com.ojvar.patientmonitoring;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ojvar.patientmonitoring.Adapters.MainMenuAdapter;
import com.ojvar.patientmonitoring.Helper.GlobalData;
import com.ojvar.patientmonitoring.Models.MainMenuItem;

public class MainActivity extends AppCompatActivity
{
	// <editor-fold defaultstate="" desc="Variables">
	ListView mainListView;
	// </editor-fold>

	// <editor-fold defaultstate="" desc="Methods">
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_main);

		init ();
	}

	/**
	 * On Resume
	 */
	@Override
	protected void onResume ()
	{
		super.onResume ();

		prepareList ();
	}

	/**
	 * Initialize
	 */
	private void init ()
	{
		findControls ();
		bindEvents ();
	}

	/**
	 * Find Controls
	 */
	private void findControls ()
	{
		mainListView = (ListView) findViewById (R.id.mainListView);
	}

	/**
	 * Bind Events
	 */
	private void bindEvents ()
	{
		if (null != mainListView)
			mainListView.setOnItemClickListener (new AdapterView.OnItemClickListener ()
			{
				@Override
				public void onItemClick (AdapterView<?> parent, View view, int position, long id)
				{
					Intent intent;

					switch (position)
					{
						// Patients Status Screen
						case 0:
							intent = new Intent (MainActivity.this, PatientStatusActivity.class);
							startActivity (intent);
							break;

						// Settings
						case 1:
							intent = new Intent (MainActivity.this, UserSettingsActivity.class);
							startActivity (intent);
							break;

						// Close
						case 2:
							Close ();
							break;
					}
				}
			});
	}

	/**
	 * Prepare list
	 */
	private void prepareList ()
	{
		if (null != mainListView)
		{
			MainMenuItem[] items = new MainMenuItem[]
					{
							new MainMenuItem (getResources ().getString (R.string.str_patients_status), R.mipmap.ic_inventory),
							new MainMenuItem (getResources ().getString (R.string.str_settings), R.mipmap.ic_setting),
							new MainMenuItem (getResources ().getString (R.string.str_exit), R.mipmap.ic_close)
					};

			MainMenuAdapter mainMenuAdapter = new MainMenuAdapter (MainActivity.this, items);
			mainListView.setAdapter (mainMenuAdapter);
		}

		// Clear client event
		GlobalData.getClient ().setClientEvent (null);
	}

	/**
	 * Close
	 */
	private void Close ()
	{
		finish ();
	}
	// </editor-fold>
}
