package com.ojvar.patientmonitoring.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.ojvar.patientmonitoring.Models.LoginSettingData;
import com.ojvar.patientmonitoring.R;

/**
 * Created by silvernight on 10/11/16.
 */

public class GlobalData
{
	// <editor-fold defaultstate="" desc="Constants">
	public static final int C_RES_OK     = 0;
	public static final int C_RES_CANCEL = 1;
	// </editor-fold>

	// <editor-fold defaultstate="" desc="Variables">
	public static String username = "";
	public static String password = "";
	// </editor-fold>

	// <editor-fold defaultstate="" desc="Methods">

	/**
	 * Save Setting
	 *
	 * @param context
	 */
	public static void saveLoginSetting (Context context, LoginSettingData data)
	{
		if ((null != context) && (null != data))
		{
			SharedPreferences        sharedPreferences = context.getSharedPreferences (context.getResources ().getString (R.string.key_setting_login_data), Context.MODE_PRIVATE);
			SharedPreferences.Editor editor            = sharedPreferences.edit ();

			editor.putString (context.getResources ().getString (R.string.key_setting_ip), data.getIp ());
			editor.putInt (context.getResources ().getString (R.string.key_setting_port), data.getPort ());
			editor.commit ();
		}
	}

	/**
	 * Load Setting
	 *
	 * @param context
	 */
	public static void loadLoginSetting (Context context, LoginSettingData data)
	{
		if ((null != context) && (null != data))
		{
			SharedPreferences sharedPreferences = context.getSharedPreferences (context.getResources ().getString (R.string.key_setting_login_data), Context.MODE_PRIVATE);

			data.setIp (sharedPreferences.getString (context.getResources ().getString (R.string.key_setting_ip), "").toString ());
			data.setPort (sharedPreferences.getInt (context.getResources ().getString (R.string.key_setting_port), 0));
		}
	}
	// </editor-fold>
}
