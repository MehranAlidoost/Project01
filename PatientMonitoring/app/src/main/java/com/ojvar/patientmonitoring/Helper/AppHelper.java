package com.ojvar.patientmonitoring.Helper;

/**
 * Created by silvernight on 10/11/16.
 */

public class AppHelper
{
	/**
	 * Close App Completely
	 */
	public static void closeApp ()
	{
		int pid = android.os.Process.myPid ();
		android.os.Process.killProcess (pid);
	}
}
