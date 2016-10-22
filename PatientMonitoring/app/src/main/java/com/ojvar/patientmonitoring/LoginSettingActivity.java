package com.ojvar.patientmonitoring;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ojvar.patientmonitoring.Helper.GlobalData;
import com.ojvar.patientmonitoring.Models.ConnectionData;

public class LoginSettingActivity extends AppCompatActivity
{
	// <editor-fold defaultstate="" desc="Varaibles">
	EditText serverIpEditText;
	EditText serverPortEditText;
	Button   saveButton;
	Button   exitButton;

	Toast lastToast;
	// </editor-fold>

	// <editor-fold defaultstate="" desc="Methods">

	/**
	 * Create activity
	 *
	 * @param savedInstanceState
	 */
	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_login_setting);

		init ();
	}

	/**
	 * Initialize
	 */
	private void init ()
	{
		findControls ();
		bindEvents ();

		loadLoginData ();
	}

	/**
	 * Find Controls
	 */
	private void findControls ()
	{
		serverIpEditText = (EditText) findViewById (R.id.serverIpEditText);
		serverPortEditText = (EditText) findViewById (R.id.serverPortEditText);
		saveButton = (Button) findViewById (R.id.saveButton);
		exitButton = (Button) findViewById (R.id.exitButton);
	}

	/**
	 * Bind events
	 */
	private void bindEvents ()
	{
		if (null != saveButton)
			saveButton.setOnClickListener (new View.OnClickListener ()
			{
				@Override
				public void onClick (View v)
				{
					saveLoginData ();
				}
			});

		if (null != exitButton)
			exitButton.setOnClickListener (new View.OnClickListener ()
			{
				@Override
				public void onClick (View v)
				{
					setResult (GlobalData.C_RES_CANCEL);
					Close ();
				}
			});
	}

	/**
	 * Save login data
	 */
	private void saveLoginData ()
	{
		String err  = "";
		String ip   = "";
		int    port = 0;

		// <editor-fold defaultstate="" desc="Get data">
		if (null != serverIpEditText)
			ip = serverIpEditText.getText ().toString ();
		if (null != serverPortEditText)
			port = Integer.valueOf (serverPortEditText.getText ().toString ());
		// </editor-fold>

		// <editor-fold defaultstate="" desc="Validate">
		if (ip.length () == 0)
			err += "\n" + getResources ().getString (R.string.err_server_host_empty);
		// </editor-fold>

		// <editor-fold defaultstate="" desc="Save Setting">
		if (null != lastToast)
			lastToast.cancel ();

		if (err.length () > 0)
		{
			lastToast = Toast.makeText (LoginSettingActivity.this, err, Toast.LENGTH_LONG);
			lastToast.show ();
		}
		else
		{
			ConnectionData data = new ConnectionData (ip, port);
			GlobalData.saveLoginSetting (LoginSettingActivity.this, data);

			lastToast = Toast.makeText (LoginSettingActivity.this, getString (R.string.str_login_setting_change_successfully), Toast.LENGTH_LONG);
			lastToast.show ();

			setResult (GlobalData.C_RES_OK);
			Close ();
		}
		// </editor-fold>
	}

	/**
	 * Load login data
	 */
	private void loadLoginData ()
	{
		ConnectionData data = new ConnectionData ("", 0);
		GlobalData.loadLoginSetting (LoginSettingActivity.this, data);

		if (null != serverIpEditText)
			serverIpEditText.setText (data.getHost ());
		if (null != serverPortEditText)
			serverPortEditText.setText (String.valueOf (data.getPort ()));
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
