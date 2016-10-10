package com.testapp.oneapp.Utility;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Arrays;

/**
 * Client Listener
 */
public class ClientListener
{

// <editor-fold defaultstate="collapsed" desc="Constants">
	/**
	 * Log Tag
	 */
	private static final String C_LOG_TAG = "Ojvar/ClientListener";
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Variables">
	/**
	 * Socket
	 */
	private Socket client;

	/**
	 * Listen Thread
	 */
	private Thread listenThread;

	/**
	 * Buffer Length
	 */
	private int bufferLength;

	/**
	 * Receiver Event
	 */
	private IClientEvent receiverEvent;
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Properties">

	/**
	 * Get Buffer Length
	 *
	 * @return buffer length
	 */
	public int getBufferLength ()
	{
		return bufferLength;
	}

	/**
	 * Set Buffer length
	 *
	 * @param bufferLength Integer Value
	 */
	public void setBufferLength (int bufferLength)
	{
		this.bufferLength = bufferLength;
	}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Methods">

	/**
	 * Constructor
	 *
	 * @param socket
	 */
	public ClientListener (Socket socket, IClientEvent receiverEvent)
	{
		this.client = socket;
		this.receiverEvent = receiverEvent;

		if ((null != socket) && (null != receiverEvent))
			beginListen ();
	}

	/**
	 * Begin Listen
	 */
	private void beginListen ()
	{
		if (null != client)
		{
			listenThread = new Thread (new Runnable ()
			{
				@Override
				public void run ()
				{
					try
					{
						InputStream inputStream = client.getInputStream ();


						byte[] data = new byte[bufferLength];
						int    size;

						while ((null != client) && (null != listenThread) && listenThread.isAlive ())
						{
							size = inputStream.read (data, 0, bufferLength);

							if (0 < size)
							{
								byte[] newData;

								if (size == bufferLength)
									newData = data;
								else
									newData = Arrays.copyOfRange (data, 0, size);

								if (null != receiverEvent)
									receiverEvent.onReceiveData (this, newData, newData.length);
							}
						}
					}
					catch (IOException e)
					{
						e.printStackTrace ();
					}
				}
			});

			listenThread.start ();
		}
	}

	/**
	 * Stop Listen
	 */
	public void stop ()
	{
		if (null != listenThread)
		{
			try
			{
				listenThread.interrupt ();
				listenThread = null;
			}
			catch (Exception ex)
			{
				Log.e (C_LOG_TAG, ex.getMessage ());
			}
		}
	}

// </editor-fold>
}