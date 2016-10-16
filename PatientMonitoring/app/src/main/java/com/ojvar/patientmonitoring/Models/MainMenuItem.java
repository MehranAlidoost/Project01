package com.ojvar.patientmonitoring.Models;

/**
 * Created by silvernight on 10/11/16.
 */
public class MainMenuItem
{
	// <editor-fold defaultstate="" desc="Fields">
	private String text;
	private int    icon;
	// </editor-fold>

	// <editor-fold defaultstate="" desc="Methods">
	public MainMenuItem (String text, int icon)
	{
		this.text = text;
		this.icon = icon;
	}
	// </editor-fold>

	// <editor-fold defaultstate="" desc="Properties">

	/**
	 * Get Text
	 *
	 * @return
	 */
	public String getText ()
	{
		return text;
	}

	/**
	 * Set Text
	 *
	 * @param text
	 */
	public void setText (String text)
	{
		this.text = text;
	}

	/**
	 * Get Icon
	 *
	 * @return
	 */
	public int getIcon ()
	{
		return icon;
	}

	/**
	 * Set Icon
	 *
	 * @param icon
	 */
	public void setIcon (int icon)
	{
		this.icon = icon;
	}
	// </editor-fold>
}
