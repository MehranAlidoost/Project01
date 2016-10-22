package com.ojvar.patientmonitoring;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
=======
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
>>>>>>> 197f11fcead1a97864ebc9fd0a075e35c7c0a74f
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ojvar.patientmonitoring.Helper.GlobalData;
<<<<<<< HEAD

public class LoginActivity extends AppCompatActivity
{
=======
import com.ojvar.patientmonitoring.Helper.TcpClient;
import com.ojvar.patientmonitoring.Interfaces.IClientEvent;
import com.ojvar.patientmonitoring.Models.ConnectionData;

public class LoginActivity extends AppCompatActivity
{
	// <editor-fold defaultstate="" desc="Constants">
	private static final int C_SETTING_REQ_CODE = 10000;
	// </editor-fold>

>>>>>>> 197f11fcead1a97864ebc9fd0a075e35c7c0a74f
	// <editor-fold defaultstate="" desc="Variables">
	EditText usernameEditText;
	EditText passwordEditText;
	Button   loginButton;
	Button   exitButton;

<<<<<<< HEAD
	Toast lastToast;
	// </editor-fold>

=======
	ConnectionData loginData;
	Toast          lastToast;
	Handler        handler;
	// </editor-fold>

	// <editor-fold defaultstate="" desc="Methods">

>>>>>>> 197f11fcead1a97864ebc9fd0a075e35c7c0a74f
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
<<<<<<< HEAD
=======
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
		{
			GlobalData.disconnect (null);
			GlobalData.loadLoginSetting (LoginActivity.this, loginData);
		}
	}

	/**
>>>>>>> 197f11fcead1a97864ebc9fd0a075e35c7c0a74f
	 * Init
	 */
	private void init ()
	{
		findControls ();
		bindEvents ();
<<<<<<< HEAD
=======

		prepare ();
>>>>>>> 197f11fcead1a97864ebc9fd0a075e35c7c0a74f
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
<<<<<<< HEAD
=======
	 * Prepare
	 */
	private void prepare ()
	{
		handler = new Handler ();
		loginData = new ConnectionData ("", 0);
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
>>>>>>> 197f11fcead1a97864ebc9fd0a075e35c7c0a74f
	 * Check Login
	 */
	private void checkLogin ()
	{
<<<<<<< HEAD
		String err      = "";
		String username = "";
		String password = "";

		if (null != usernameEditText)
			username = usernameEditText.getText ().toString ();
		if (null != passwordEditText)
			password = passwordEditText.getText ().toString ();
=======
		String       err = "";
		final String username;
		final String password;

		if (null != usernameEditText)
			username = usernameEditText.getText ().toString ();
		else
			username = "";
		if (null != passwordEditText)
			password = passwordEditText.getText ().toString ();
		else
			password = "";
>>>>>>> 197f11fcead1a97864ebc9fd0a075e35c7c0a74f

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
<<<<<<< HEAD
			lastToast   = Toast.makeText (this, err, Toast.LENGTH_SHORT);
=======
			lastToast = Toast.makeText (this, err, Toast.LENGTH_SHORT);
>>>>>>> 197f11fcead1a97864ebc9fd0a075e35c7c0a74f
			lastToast.show ();
		}
		else
		{
<<<<<<< HEAD
			// <editor-fold defaultstate="" desc="Check User data">

			// If user data is valid
			showMainActitiy ();
=======
			// <editor-fold defaultstate="" desc="Try to connect to host">
			ConnectionData data = new ConnectionData ("", 0);
			GlobalData.loadLoginSetting (LoginActivity.this, data);

			// <editor-fold defaultstate="" desc="Check User data">
			if (null != lastToast)
				lastToast.cancel ();

			if (GlobalData.getClient () == null)
				GlobalData.connect (data, new IClientEvent ()
				{
					@Override
					public void onReceiveData (TcpClient sender, byte[] data, int size)
					{
					}

					@Override
					public void onConnect (TcpClient sender)
					{
						handler.post (new Runnable ()
						{
							@Override
							public void run ()
							{
								if (null != lastToast)
									lastToast.cancel ();

								lastToast = Toast.makeText (LoginActivity.this, getResources ().getString (R.string.str_connecting_to_server), Toast.LENGTH_SHORT);
								lastToast.show ();
							}
						});

						// Setup receiver
						GlobalData.getClient ().setClientEvent (onClientReceiveData);

						// Send login data
						sendLoginData (username, password);
					}

					@Override
					public void onDisconnect (TcpClient sender)
					{
					}
				});
			else
				// Send login data
				sendLoginData (username, password);
			// </editor-fold>
>>>>>>> 197f11fcead1a97864ebc9fd0a075e35c7c0a74f

			// </editor-fold>
		}
	}

	/**
<<<<<<< HEAD
=======
	 * Client Receive data
	 */
	IClientEvent onClientReceiveData = new IClientEvent ()
	{
		@Override
		public void onReceiveData (TcpClient sender, final byte[] data, int size)
		{
			handler.post (new Runnable ()
			{
				@Override
				public void run ()
				{
					String recData = new String (data).replace ("\n", "");

					if (recData.toLowerCase ().equals (getResources ().getString (R.string.cmd_server_ok)))
						showMainActitiy ();
					else
					{
						if (null != lastToast)
							lastToast.cancel ();

						lastToast = Toast.makeText (LoginActivity.this, getResources ().getString (R.string.err_login_failed), Toast.LENGTH_SHORT);
						lastToast.show ();
					}
				}
			});
		}

		@Override
		public void onConnect (TcpClient sender)
		{
		}

		@Override
		public void onDisconnect (TcpClient sender)
		{
		}
	};

	/**
	 * Send Login data
	 */
	private void sendLoginData (final String username, final String password)
	{
		GlobalData.getClient ().write (String.format (getResources ().getString (R.string.cmd_login_command), username, password));
	}

	/**
>>>>>>> 197f11fcead1a97864ebc9fd0a075e35c7c0a74f
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
<<<<<<< HEAD
		GlobalData.username = username;
		GlobalData.password = password;

		// Show main activity
		Intent  intent  = new Intent (LoginActivity.this, MainWindow.class);
=======
		GlobalData.setUsername (username);
		GlobalData.setPassword (password);

		// Show main activity
		Intent intent = new Intent (LoginActivity.this, MainActivity.class);
>>>>>>> 197f11fcead1a97864ebc9fd0a075e35c7c0a74f
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
<<<<<<< HEAD
=======
	// </editor-fold>
>>>>>>> 197f11fcead1a97864ebc9fd0a075e35c7c0a74f
}
