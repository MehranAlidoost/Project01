package com.ojvar.patientmonitoring.Models;

/**
 * Login Setting Data Model
 */
public class ConnectionData
{
	// <editor-fold defaultstate="" desc="Variables">
	private String host;
	private int    port;
	// </editor-fold>

	// <editor-fold defaultstate="" desc="Methods">
	public ConnectionData (String host, int port)
	{
		setHost (host);
		setPort (port);
	}

	/**
	 * Get IP
	 *
	 * @return
	 */
	public String getHost ()
	{
		return host;
	}

	/**
	 * Set IP
	 *
	 * @param host
	 */
	public void setHost (String host)
	{
		this.host = host;
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
