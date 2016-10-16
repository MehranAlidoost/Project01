package com.ojvar.patientmonitoring;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ojvar.patientmonitoring.Helper.GlobalData;
import com.ojvar.patientmonitoring.Helper.TcpClient;
import com.ojvar.patientmonitoring.Interfaces.IClientEvent;

public class UserSettingsActivity extends AppCompatActivity
{
	// <editor-fold defaultstate="" desc="Variables">
	EditText currentPasswordEditText;
	EditText newPassword1EditText;
	EditText newPassword2EditText;
	Button   changePasswordButton;
	Button   exitButton;

	Handler handler;
	Toast   lastToast;
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
		setContentView (R.layout.activity_user_settings);

		init ();
	}

	/**
	 * Initialize
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
		currentPasswordEditText = (EditText) findViewById (R.id.currentPasswordEditText);
		newPassword1EditText = (EditText) findViewById (R.id.newPassword1EditText);
		newPassword2EditText = (EditText) findViewById (R.id.newPassword2EditText);
		changePasswordButton = (Button) findViewById (R.id.changePasswordButton);
		exitButton = (Button) findViewById (R.id.exitButton);
	}

	/**
	 * Bind Events
	 */
	private void bindEvents ()
	{
		if (null != exitButton)
			exitButton.setOnClickListener (new View.OnClickListener ()
			{
				@Override
				public void onClick (View v)
				{
					Close ();
				}
			});

		if (null != changePasswordButton)
			changePasswordButton.setOnClickListener (new View.OnClickListener ()
			{
				@Override
				public void onClick (View v)
				{
					changePassword ();
				}
			});
	}

	/**
	 * Prepare
	 */
	private void prepare ()
	{
		handler = new Handler ();

		if (null != GlobalData.getClient ())
			GlobalData.getClient ().setClientEvent (new IClientEvent ()
			{
				@Override
				public void onReceiveData (TcpClient sender, byte[] data, int size)
				{
					String cmd = new String (data).trim ().replace ("\n", "");

					// Parse command
					String[] cmdPart = cmd.split ("\r");

					if (cmdPart.length > 0)
					{
						String cmdType = cmdPart[0].toLowerCase ().trim ();

						if (null != lastToast)
							lastToast.cancel ();

						if (cmdType.equals (getResources ().getString (R.string.cmd_server_ok)))
						{
							handler.post (new Runnable ()
							{
								@Override
								public void run ()
								{
									lastToast = Toast.makeText (UserSettingsActivity.this, getResources ().getString (R.string.str_passwd_successfully_changed), Toast.LENGTH_LONG);
									lastToast.show ();

									Close ();
								}
							});
						}
						else if (cmdType.equals (getResources ().getString (R.string.cmd_server_failed)))
						{
							handler.post (new Runnable ()
							{
								@Override
								public void run ()
								{
									lastToast = Toast.makeText (UserSettingsActivity.this, getResources ().getString (R.string.str_passwd_successfully_failed), Toast.LENGTH_LONG);
									lastToast.show ();
								}
							});
						}
					}
				}

				@Override
				public void onConnect (TcpClient sender)
				{
				}

				@Override
				public void onDisconnect (TcpClient sender)
				{
				}
			});
	}

	/**
	 * Change Password
	 */
	private void changePassword ()
	{
		String err      = "";
		String curPass  = "";
		String newPass1 = "";
		String newPass2 = "";

		// <editor-fold defaultstate="" desc="Get Values">
		if (null != currentPasswordEditText)
			curPass = currentPasswordEditText.getText ().toString ();
		if (null != newPassword1EditText)
			newPass1 = newPassword1EditText.getText ().toString ();
		if (null != newPassword2EditText)
			newPass2 = newPassword2EditText.getText ().toString ();
		// </editor-fold>

		// <editor-fold defaultstate="" desc="Validate">
		if (curPass.length () == 0)
			err += "\n" + getResources ().getString (R.string.err_cur_pass_empty);
		if (newPass1.length () == 0)
			err += "\n" + getResources ().getString (R.string.err_new_pass_empty);
		else if (!newPass2.equals (newPass1))
			err += "\n" + getResources ().getString (R.string.err_new_pass_repeat_not_valid);
		// </editor-fold>

		// <editor-fold defaultstate="" desc="Try To Change">
		if (null != lastToast)
			lastToast.cancel ();

		if (err.length () > 0)
		{
			lastToast = Toast.makeText (UserSettingsActivity.this, err.substring (1), Toast.LENGTH_LONG);
			lastToast.show ();
		}
		else
		{
			if (null != GlobalData.getClient ())
			{
				GlobalData.getClient ().write (String.format (getResources ().getString (R.string.cmd_passwd), GlobalData.getUsername (), curPass, newPass1));

				lastToast = Toast.makeText (UserSettingsActivity.this, getResources ().getString (R.string.str_send_request_loading), Toast.LENGTH_LONG);
				lastToast.show ();
			}
		}
		// </editor-fold>
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