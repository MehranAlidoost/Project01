package com.testapp.oneapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity
{

	Button   enterButton;
	EditText userNameEditText, passwordEditText;
	Intent intent;

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_login);
		getSupportActionBar ().setTitle (getString (R.string.en_login));

		init ();

		enterButton.setOnClickListener (new View.OnClickListener ()
		{
			@Override
			public void onClick (View view)
			{
				intent = new Intent (LoginActivity.this, MainActivity.class);
				startActivity (intent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu (Menu menu)
	{
		getMenuInflater ().inflate (R.menu.login_optionmenu, menu);
		return super.onCreateOptionsMenu (menu);
	}

	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{
		switch (item.getItemId ())
		{
			case R.id.setting:
				// do
				intent = new Intent (LoginActivity.this, SettingActivity.class);
				startActivity (intent);
				return true;

			case R.id.about:
				// do
				intent = new Intent (LoginActivity.this, AboutActivity.class);
				startActivity (intent);
				return true;

			case R.id.exit:
				System.exit (10);
				return true;

			default:
				return super.onOptionsItemSelected (item);

		}
	}

	/**
	 * initialize
	 */
	private void init ()
	{

		userNameEditText = (EditText) findViewById (R.id.loginActivityUserNameEditText);
		passwordEditText = (EditText) findViewById (R.id.loginActivityPasswordEditText);
		enterButton = (Button) findViewById (R.id.loginActivityEnterButton);

	}
}
