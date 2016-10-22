package com.ojvar.patientmonitoring.Helper;

<<<<<<< HEAD
=======
import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.ojvar.patientmonitoring.Interfaces.IClientEvent;
import com.ojvar.patientmonitoring.Models.ConnectionData;
import com.ojvar.patientmonitoring.R;

>>>>>>> 197f11fcead1a97864ebc9fd0a075e35c7c0a74f
/**
 * Created by silvernight on 10/11/16.
 */

public class GlobalData
{
<<<<<<< HEAD
	public static String username = "";
	public static String password = "";
=======
	// <editor-fold defaultstate="" desc="Constants">
	public static final int C_RES_OK     = 0;
	public static final int C_RES_CANCEL = 1;
	// </editor-fold>

	// <editor-fold defaultstate="" desc="Variables">
	private static String    username;
	private static String    password;
	private static TcpClient client;
	// </editor-fold>

	// <editor-fold defaultstate="" desc="Properties">

	/**
	 * Show Animation
	 */
	public static void applyAnimation (Context context, View view, int anim)
	{
		Animation a = AnimationUtils.loadAnimation (context, anim);

		if ((a != null) && (null != view))
		{
			a.reset ();

			view.clearAnimation ();
			view.startAnimation (a);
		}
	}
	/**
	 * Get Username
	 *
	 * @return
	 */
	public static String getUsername ()
	{
		return username;
	}

	/**
	 * Set Username
	 *
	 * @param username
	 */
	public static void setUsername (String username)
	{
		GlobalData.username = username;
	}

	/**
	 * Get Password
	 *
	 * @return
	 */
	public static String getPassword ()
	{
		return password;
	}

	/**
	 * Set Password
	 *
	 * @param password
	 */
	public static void setPassword (String password)
	{
		GlobalData.password = password;
	}

	/**
	 * Get Client
	 *
	 * @return
	 */
	public static TcpClient getClient ()
	{
		return client;
	}

	/**
	 * Set Client
	 *
	 * @param client
	 */
	public static void setClient (TcpClient client)
	{
		GlobalData.client = client;
	}
	// </editor-fold>

	// <editor-fold defaultstate="" desc="Methods">

	/**
	 * Connect
	 */
	public static void connect (ConnectionData data, IClientEvent event)
	{
		if ((client == null) || client.isClosed ())
		{
			if (client == null)
				client = new TcpClient ();
			client.connect (data, event);
		}
	}

	/**
	 * Disconnect
	 */
	public static void disconnect (IClientEvent event)
	{
		if ((client != null) && client.isConnected ())
			client.disconnect (event);
		client = null;
	}

	/**
	 * Save Setting
	 *
	 * @param context
	 */
	public static void saveLoginSetting (Context context, ConnectionData data)
	{
		if ((null != context) && (null != data))
		{
			SharedPreferences        sharedPreferences = context.getSharedPreferences (context.getResources ().getString (R.string.key_setting_login_data), Context.MODE_PRIVATE);
			SharedPreferences.Editor editor            = sharedPreferences.edit ();

			editor.putString (context.getResources ().getString (R.string.key_setting_ip), data.getHost ());
			editor.putInt (context.getResources ().getString (R.string.key_setting_port), data.getPort ());
			editor.commit ();
		}
	}

	/**
	 * Load Setting
	 *
	 * @param context
	 */
	public static void loadLoginSetting (Context context, ConnectionData data)
	{
		if ((null != context) && (null != data))
		{
			SharedPreferences sharedPreferences = context.getSharedPreferences (context.getResources ().getString (R.string.key_setting_login_data), Context.MODE_PRIVATE);

			data.setHost (sharedPreferences.getString (context.getResources ().getString (R.string.key_setting_ip), "").toString ());
			data.setPort (sharedPreferences.getInt (context.getResources ().getString (R.string.key_setting_port), 0));
		}
	}

	// </editor-fold>
>>>>>>> 197f11fcead1a97864ebc9fd0a075e35c7c0a74f
}
