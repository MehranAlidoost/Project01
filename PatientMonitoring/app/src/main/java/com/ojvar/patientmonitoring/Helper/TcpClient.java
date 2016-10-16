package com.ojvar.patientmonitoring.Helper;

import android.os.AsyncTask;
import android.util.Log;

import com.ojvar.patientmonitoring.Interfaces.IClientEvent;
import com.ojvar.patientmonitoring.Models.ConnectionData;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Arrays;

/**
 * TcpClient
 */
public class TcpClient
{
	// <editor-fold defaultstate="" desc="Constants">
	private final String C_LOG_TAG     = "Ojvar/TcpClient";
	private final int    C_BUFFER_SIZE = 1024;
	// </editor-fold>

	// <editor-fold defaultstate="" desc="Variables">
	Socket       socket;
	OutputStream outStream;
	InputStream  inStream;

	ConnectionData connectionData;
	Thread         listenThread;

	IClientEvent clientEvent;
	// </editor-fold>

	// <editor-fold defaultstate="" desc="Properties">

	/**
	 * Get Client Event
	 *
	 * @return
	 */
	public IClientEvent getClientEvent ()
	{
		return clientEvent;
	}

	/**
	 * Set Client Event
	 *
	 * @param clientEvent
	 */
	public void setClientEvent (IClientEvent clientEvent)
	{
		this.clientEvent = clientEvent;
	}

	/**
	 * Client connection status
	 *
	 * @return
	 */
	public boolean isConnected ()
	{
		return (socket != null) && socket.isConnected () && !socket.isClosed ();
	}

	/**
	 * Client connection status
	 *
	 * @return
	 */
	public boolean isClosed ()
	{
		return (socket != null) && !socket.isConnected () && socket.isClosed ();
	}
	// </editor-fold>

	// <editor-fold defaultstate="" desc="Methods">

	/**
	 * Constructor
	 */
	public TcpClient ()
	{
		socket = new Socket ();
		connectionData = new ConnectionData ("", 0);
	}


	/**
	 * Connect
	 *
	 * @return
	 */
	public void connect (ConnectionData data, final IClientEvent onConnectEvent)
	{
		if ((data != null) && (null != socket) && (!socket.isConnected () || socket.isClosed ()))
		{
			this.connectionData = data;

			Thread connectThread = new Thread (new Runnable ()
			{
				@Override
				public void run ()
				{
					// Stop listen thread
					stopListenThread ();

					try
					{
						socket.connect (new InetSocketAddress (connectionData.getHost (), connectionData.getPort ()));

						// Get output & input stream
						outStream = socket.getOutputStream ();
						inStream = socket.getInputStream ();

						// Prepare thread
						startListenThread ();

						// Fire event
						if (null != onConnectEvent)
							onConnectEvent.onConnect (TcpClient.this);
					}
					catch (Exception ex)
					{
						Log.e (C_LOG_TAG, ex.getMessage ());
					}
				}
			});

			connectThread.start ();
		}
	}

	/**
	 * Disconnect
	 */
	public void disconnect (IClientEvent onDisconnectEvent)
	{
		try
		{
			if ((null != socket) && socket.isConnected () || !socket.isClosed ())
			{
				// Prepare thread
				stopListenThread ();

				// Get output & input stream
				outStream = null;
				inStream = null;

				// Close Socket
				socket.close ();

				// Fire event
				if (null != onDisconnectEvent)
					onDisconnectEvent.onConnect (TcpClient.this);
			}
		}
		catch (Exception ex)
		{
			Log.e (C_LOG_TAG, ex.getMessage ());
		}
	}

	/**
	 * Start listen thread
	 */
	private void startListenThread ()
	{
		try
		{
			stopListenThread ();


			listenThread = new Thread (new Runnable ()
			{
				@Override
				public void run ()
				{
					int    size;
					byte[] data = new byte[C_BUFFER_SIZE];

					while ((null != socket) && socket.isConnected () && !socket.isClosed ())
					{
						if (null != inStream)
						{
							try
							{
								size = inStream.read (data, 0, data.length);

								if ((size > 0) && (clientEvent != null))
								{
									byte[]  cropedData  = Arrays.copyOfRange (data, 0, size);
									clientEvent.onReceiveData (TcpClient.this, cropedData, size);
								}
							}
							catch (Exception ex)
							{
								Log.e (C_LOG_TAG, ex.getMessage ());
							}
						}
					}
				}
			});

			listenThread.start ();
		}
		catch (Exception ex)
		{
			Log.e (C_LOG_TAG, ex.getMessage ());
		}
	}

	/**
	 * Stop listen thread
	 */
	private void stopListenThread ()
	{
		if (null != listenThread)
			try
			{
				listenThread.interrupt ();
			}
			catch (Exception ex)
			{
				Log.e (C_LOG_TAG, ex.getMessage ());
			}
			finally
			{
				listenThread = null;
			}
	}

	/**
	 * Write data
	 *
	 * @param data
	 */
	public void write (byte[] data)
	{
		if (null != socket)
			try
			{
				outStream.write (data, 0, data.length);
			}
			catch (Exception ex)
			{
				Log.e (C_LOG_TAG, ex.getMessage ());
			}
	}

	/**
	 * Write string
	 *
	 * @param data
	 */
	public void write (String data)
	{
		if (null != socket)
			try
			{
				byte[] buffer = data.getBytes ();
				outStream.write (buffer, 0, buffer.length);
			}
			catch (Exception ex)
			{
				Log.e (C_LOG_TAG, ex.getMessage ());
			}
	}
	// </editor-fold>
}
