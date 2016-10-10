package com.testapp.oneapp.Utility;

/**
 * Client Event
 */
public interface IClientEvent
{
	/**
	 * On Receive data event
	 */
	void onReceiveData (Object sender, byte[] data, int len);
}
