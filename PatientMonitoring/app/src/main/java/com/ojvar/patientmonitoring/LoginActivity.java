package com.ojvar.patientmonitoring;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ojvar.patientmonitoring.Helper.GlobalData;

public class LoginActivity extends AppCompatActivity
{
	// <editor-fold defaultstate="" desc="Variables">
	EditText usernameEditText;
	EditText passwordEditText;
	Button   loginButton;
	Button   exitButton;

	Toast lastToast;
	// </editor-fold>

	/**
	 * Create Activity
	 *
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_login);

		init ();
	}

	/**
	 * Create Option Menu
	 *
	 * @param menu
	 *
	 * @return
	 */
	@Override
	public boolean onCreateOptionsMenu (Menu menu)
	{
		getMenuInflater ().inflate (R.menu.login_menu, menu);
		return super.onCreateOptionsMenu (menu);
	}

	/**
	 * Init
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
		loginButton = (Button) findViewById (R.id.loginButton);
		exitButton = (Button) findViewById (R.id.exitButton);
		usernameEditText = (EditText) findViewById (R.id.usernameEditText);
		passwordEditText = (EditText) findViewById (R.id.passwordEditText);
	}

	/**
	 * Bind Events
	 */
	private void bindEvents ()
	{
		if (null != loginButton)
			loginButton.setOnClickListener (new View.OnClickListener ()
			{
				@Override
				public void onClick (View v)
				{
					checkLogin ();
				}
			});

		if (null != exitButton)
			exitButton.setOnClickListener (new View.OnClickListener ()
			{
				@Override
				public void onClick (View v)
				{
					close ();
				}
			});
	}

	/**
	 * Check Login
	 */
	private void checkLogin ()
	{
		String err      = "";
		String username = "";
		String password = "";

		if (null != usernameEditText)
			username = usernameEditText.getText ().toString ();
		if (null != passwordEditText)
			password = passwordEditText.getText ().toString ();

		// <editor-fold defaultstate="" desc="Validate">
		if (username.length () == 0)
			err += "\n" + getResources ().getString (R.string.err_empty_username);
		if (password.length () == 0)
			err += "\n" + getResources ().getString (R.string.err_empty_password);
		// </editor-fold>

		if (err.length () != 0)
		{
			if (null != lastToast)
				lastToast.cancel ();

			err = err.substring (1);
			lastToast   = Toast.makeText (this, err, Toast.LENGTH_SHORT);
			lastToast.show ();
		}
		else
		{
			// <editor-fold defaultstate="" desc="Check User data">

			// If user data is valid
			showMainActitiy ();

			// </editor-fold>
		}
	}

	/**
	 * Show Main Activity
	 */
	private void showMainActitiy ()
	{
		String username = "";
		String password = "";

		if (null != usernameEditText)
			username = usernameEditText.getText ().toString ();
		if (null != passwordEditText)
			password = passwordEditText.getText ().toString ();

		// Save user data
		GlobalData.username = username;
		GlobalData.password = password;

		// Show main activity
		Intent  intent  = new Intent (LoginActivity.this, MainWindow.class);
		startActivity (intent);
	}

	/**
	 * Close
	 */
	private void close ()
	{
		finish ();
		System.exit (0);
	}
}
