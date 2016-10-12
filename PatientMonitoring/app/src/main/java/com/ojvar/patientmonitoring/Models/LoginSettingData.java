package com.ojvar.patientmonitoring.Models;

/**
 * Login Setting Data Model
 */
public class LoginSettingData
{
	// <editor-fold defaultstate="" desc="Variables">
	private String ip;
	private int    port;
	// </editor-fold>

	// <editor-fold defaultstate="" desc="Methods">
	public LoginSettingData (String ip, int port)
	{
		this.ip = ip;
		this.port = port;
	}

	/**
	 * Get IP
	 *
	 * @return
	 */
	public String getIp ()
	{
		return ip;
	}

	/**
	 * Set IP
	 *
	 * @param ip
	 */
	public void setIp (String ip)
	{
		this.ip = ip;
	}

	/**
	 * Get Port
	 *
	 * @return
	 */
	public int getPort ()
	{
		return port;
	}

	/**
	 * Set Port
	 *
	 * @param port
	 */
	public void setPort (int port)
	{
		this.port = port;
	}
	// </editor-fold>
}
