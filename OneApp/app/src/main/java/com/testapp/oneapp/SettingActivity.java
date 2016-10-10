package com.testapp.oneapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Setting Activity
 */
public class SettingActivity extends AppCompatActivity
{

// <editor-fold defaultstate="" desc="Variables">
	EditText ipEditText;
	EditText portEditText;
	Button   saveButton;
	Button   backButton;
	String   ip;
	String   port;

	SharedPreferences prefs;
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Methods">
	@Override
	protected void onCreate (final Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_setting);

		// Init
		init ();
	}

	/**
	 * Initialize
	 */
	private void init ()
	{
		findControls ();
		bindEevents ();
		loadData ();
	}

	/**
	 * Bind Events
	 */
	private void bindEevents ()
	{
		if (null != saveButton) saveButton.setOnClickListener (new View.OnClickListener ()
		{
			@Override
			public void onClick (View view)
			{
				if (ipEditText.getText ().toString ().equals ("") || portEditText.getText ().toString ().equals (""))
					Toast.makeText (SettingActivity.this, R.string.fillAllFields, Toast.LENGTH_SHORT).show ();
				else
				{
					if (prefs != null)
					{
						SharedPreferences.Editor editor = prefs.edit ();

						// Save Setting
						editor.putString ("ip", ipEditText.getText ().toString ());
						editor.putString ("port", portEditText.getText ().toString ());
						editor.apply ();

						// Show Message
						Toast.makeText (SettingActivity.this, R.string.ipAndPortSaved, Toast.LENGTH_SHORT).show ();

						// Finish
						finish ();
					}
					else
						// Show Message
						Toast.makeText (SettingActivity.this, R.string.ipAndPortNotSaved, Toast.LENGTH_SHORT).show ();
				}
			}
		});

		if (null != backButton) backButton.setOnClickListener (new View.OnClickListener ()
		{
			@Override
			public void onClick (View view)
			{
				finish ();
			}
		});
	}

	/**
	 * Find Controls
	 */
	private void findControls ()
	{
		prefs = PreferenceManager.getDefaultSharedPreferences (SettingActivity.this);

		ipEditText = (EditText) findViewById (R.id.settingActivityServerIPEditText);
		portEditText = (EditText) findViewById (R.id.settingActivityServerPortEditText);
		saveButton = (Button) findViewById (R.id.settingActivitySaveButton);
		backButton = (Button) findViewById (R.id.settingActivityBackButton);
	}

	/**
	 * Load data
	 */
	private void loadData ()
	{
		if (prefs != null)
		{
			if (null != ipEditText)
				ipEditText.setText (prefs.getString ("ip", ""));
			if (null != portEditText)
				portEditText.setText (prefs.getString ("port", ""));
		}
	}
// </editor-fold>
}
