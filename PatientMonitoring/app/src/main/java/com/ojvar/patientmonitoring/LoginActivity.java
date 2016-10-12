package com.ojvar.patientmonitoring;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ojvar.patientmonitoring.Helper.GlobalData;
import com.ojvar.patientmonitoring.Models.LoginSettingData;

public class LoginActivity extends AppCompatActivity
{
	// <editor-fold defaultstate="" desc="Constants">
	private static final int C_SETTING_REQ_CODE = 10000;
	// </editor-fold>

	// <editor-fold defaultstate="" desc="Variables">
	EditText usernameEditText;
	EditText passwordEditText;
	Button   loginButton;
	Button   exitButton;


	LoginSettingData loginData;
	Toast            lastToast;
	// </editor-fold>

	// <editor-fold defaultstate="" desc="Methods">

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
	 * Select a menu item
	 *
	 * @param item
	 *
	 * @return
	 */
	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{
		boolean used = false;

		switch (item.getItemId ())
		{
			case R.id.menuSettings:
				showLoginSetting ();
				break;

			case R.id.menuAbout:
				showAbout ();
				break;

			case R.id.menuExit:
				close ();
				break;
		}

		return used;
	}

	/**
	 * On Activity Result
	 *
	 * @param requestCode
	 * @param resultCode
	 * @param data
	 */
	@Override
	protected void onActivityResult (int requestCode, int resultCode, Intent data)
	{
		if ((requestCode == C_SETTING_REQ_CODE) && (resultCode == GlobalData.C_RES_OK))
			GlobalData.loadLoginSetting (LoginActivity.this, loginData);
	}

	/**
	 * Init
	 */
	private void init ()
	{
		findControls ();
		bindEvents ();

		prepare ();
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
	 * Prepare
	 */
	private void prepare ()
	{
		loginData = new LoginSettingData ("", 0);
		GlobalData.loadLoginSetting (LoginActivity.this, loginData);
	}

	/**
	 * Show login setting activity
	 */
	private void showLoginSetting ()
	{
		Intent intent = new Intent (LoginActivity.this, LoginSettingActivity.class);
		startActivityForResult (intent, C_SETTING_REQ_CODE);
	}


	/**
	 * Show About
	 */
	private void showAbout ()
	{
		Intent intent = new Intent (LoginActivity.this, AboutActivity.class);
		startActivity (intent);
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
			lastToast = Toast.makeText (this, err, Toast.LENGTH_SHORT);
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
		Intent intent = new Intent (LoginActivity.this, MainActivity.class);
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
	// </editor-fold>
}
