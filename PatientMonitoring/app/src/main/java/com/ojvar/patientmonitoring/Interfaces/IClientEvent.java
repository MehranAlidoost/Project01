package com.ojvar.patientmonitoring.Interfaces;

import com.ojvar.patientmonitoring.Helper.TcpClient;

/**
 * Client Event Interface
 */
public interface IClientEvent
{
	/**
	 * On Receive Data
	 * @param sender
	 * @param data
	 * @param size
	 */
	void onReceiveData (TcpClient sender, byte[] data, int size);

	/**
	 * On Connect
	 * @param sender
	 */
	void onConnect (TcpClient sender);

	/**
	 * On Disconnect
	 * @param sender
	 */
	void onDisconnect (TcpClient sender);
}
