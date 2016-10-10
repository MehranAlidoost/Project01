package com.testapp.oneapp.Utility;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * (TCP)Client Class
 */
public class Client
{
// <editor-fold defaultstate="collapsed" desc="Constants">
	/**
	 * Log Tag
	 */
	private static final String C_LOG_TAG = "Ojvar/Client";
// </editor-fold>

// <editor-fold desc="Variables">
	/**
	 * Client socket
	 */
	private Socket client;

	/**
	 * Output Stream
	 */
	private OutputStream outputStream;
// </editor-fold>

// <editor-fold desc="Properties">

	/**
	 * Check connection status
	 *
	 * @return True: Connected \n False Disconnected
	 */
	public boolean isConnected ()
	{
		boolean result;

		result = (null != client) && (client.isConnected ()) && !client.isClosed ();

		return result;
	}

	/**
	 * Get Client
	 *
	 * @return Client variable
	 */
	public Socket getClient ()
	{
		return client;
	}
// </editor-fold>

// <editor-fold desc="Methods">

	/**
	 * Constructor
	 */
	public Client ()
	{
		client = new Socket ();
	}

	/**
	 * Connect to server
	 *
	 * @param host
	 * @param port
	 */
	public boolean connect (String host, int port)
	{
		boolean result;

		result = false;

		try
		{
			if (!isConnected ())
			{
				client.connect (new InetSocketAddress (host, port));

				outputStream = client.getOutputStream ();

				result = true;
			}
		}
		catch (Exception ex)
		{
			Log.e (C_LOG_TAG, ex.getMessage ());
		}

		return result;
	}

	/**
	 * DisConnect from server
	 */
	public boolean disconnect ()
	{
		boolean result;

		result = false;

		try
		{
			if (isConnected ())
			{
				if (null != outputStream)
					outputStream.close ();

				client.close ();
			}

			result = true;
		}
		catch (Exception ex)
		{
			Log.e (C_LOG_TAG, ex.getMessage ());
		}

		return result;
	}

//<editor-fold desc="Write Methods">
	/**
	 * Write
	 *
	 * @param data
	 *
	 * @return
	 */
	public boolean write (String data)
	{
		boolean result;

		result = false;
		if (null != data)
		{
			byte[] bytes = new byte[0];

			try
			{
				bytes = data.getBytes ("UTF-8");
				result = write (bytes, bytes.length);
			}
			catch (UnsupportedEncodingException ex)
			{
				Log.e (C_LOG_TAG, ex.getMessage ());
			}
		}

		return result;
	}

	/**
	 * Write
	 *
	 * @param data
	 *
	 * @return
	 */
	public boolean write (byte[] data)
	{
		boolean result;

		result = false;
		if (null != data)
			result = write (data, data.length);

		return result;
	}

	/**
	 * Write
	 *
	 * @param data
	 * @param len
	 *
	 * @return
	 */
	public boolean write (byte[] data, int len)
	{
		boolean result;

		result = false;
		if (isConnected () && (data != null) && (len > 0))
			if (null != outputStream)
			{
				try
				{
					outputStream.write (data, 0, len);
					result = true;
				}
				catch (IOException ex)
				{
					Log.e (C_LOG_TAG, ex.getMessage ());
				}
			}

		return result;
	}
// </editor-fold>

// </editor-fold>
}
