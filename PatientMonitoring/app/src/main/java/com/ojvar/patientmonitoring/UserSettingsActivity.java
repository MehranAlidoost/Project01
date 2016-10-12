package com.ojvar.patientmonitoring;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ojvar.patientmonitoring.Helper.GlobalData;

public class UserSettingsActivity extends AppCompatActivity
{
	// <editor-fold defaultstate="" desc="Variables">
	EditText currentPasswordEditText;
	EditText newPassword1EditText;
	EditText newPassword2EditText;
	Button   changePasswordButton;
	Button   exitButton;

	Toast lastToast;
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
		if ((curPass.length () == 0) || (curPass.equals (GlobalData.password)))
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
			lastToast = Toast.makeText (UserSettingsActivity.this, getResources ().getString (R.string.str_change_password_successfully), Toast.LENGTH_LONG);
			lastToast.show ();
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