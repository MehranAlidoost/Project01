package com.testapp.oneapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity
{

	EditText ipEditText, portEditText;
	Button saveButton, backButton;
	String ip, port;
	Context context = SettingActivity.this;

	SharedPreferences prefs = null;

	@Override
	protected void onCreate (final Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_setting);
		getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
		getSupportActionBar ().setTitle (R.string.en_setting);

		init ();

		prefs = PreferenceManager.getDefaultSharedPreferences (context);
		ipEditText.setText (prefs.getString ("ip", ""));
		portEditText.setText (prefs.getString ("port", ""));

		saveButton.setOnClickListener (new View.OnClickListener ()
		{
			@Override
			public void onClick (View view)
			{
				if (ipEditText.getText ().toString ().equals ("") || portEditText.getText ().toString ().equals (""))
				{
					Toast.makeText (SettingActivity.this, R.string.fillAllFields, Toast.LENGTH_SHORT).show ();
				} else
				{
					prefs = PreferenceManager.getDefaultSharedPreferences (context);
					SharedPreferences.Editor editor = prefs.edit ();
					editor.putString ("ip", ipEditText.getText ().toString ());
					editor.putString ("port", portEditText.getText ().toString ());
					editor.apply ();
					Toast.makeText (SettingActivity.this, R.string.ipAndPortSaved, Toast.LENGTH_SHORT).show ();
					finish ();
				}
			}
		});

		backButton.setOnClickListener (new View.OnClickListener ()
		{
			@Override
			public void onClick (View view)
			{
				finish ();
			}
		});


	}
	
	/**
	 * initialize
	 */
	private void init ()
	{

		ipEditText = (EditText) findViewById (R.id.settingActivityServerIPEditText);
		portEditText = (EditText) findViewById (R.id.settingActivityServerPortEditText);
		saveButton = (Button) findViewById (R.id.settingActivitySaveButton);
		backButton = (Button) findViewById (R.id.settingActivityBackButton);

	}

	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{
		this.finish ();
		return false;
	}

}
