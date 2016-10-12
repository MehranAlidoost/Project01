package com.ojvar.patientmonitoring.Models;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ojvar.patientmonitoring.R;

/**
 * Created by silvernight on 10/11/16.
 */

public class MainMenuItemHolder
{
	// <editor-fold defaultstate="" desc="Variables">
	private TextView  titleTextView;
	private ImageView iconImageView;
	// </editor-fold>

	// <editor-fold defaultstate="" desc="Properties">

	// <editor-fold defaultstate="" desc="Methods">
	public MainMenuItemHolder (View view, String title, int icon)
	{
		if (null != view)
		{
			this.titleTextView = (TextView) view.findViewById (R.id.titleTextView);
			this.iconImageView = (ImageView) view.findViewById (R.id.iconImageView);

			this.titleTextView.setText (title);
			this.iconImageView.setImageResource (icon);
		}
	}

	/**
	 * Get TileTextView
	 *
	 * @return
	 */
	public TextView getTitleTextView ()
	{
		return titleTextView;
	}

	/**
	 * Set TileTextView
	 *
	 * @param titleTextView
	 */
	public void setTitleTextView (TextView titleTextView)
	{
		this.titleTextView = titleTextView;
	}

	/**
	 * Get Icon ImageView
	 *
	 * @return
	 */
	public ImageView getIconImageView ()
	{
		return iconImageView;
	}
	// </editor-fold>

	/**
	 * Set Icon ImageView
	 *
	 * @param iconImageView
	 */
	public void setIconImageView (ImageView iconImageView)
	{
		this.iconImageView = iconImageView;
	}
	// </editor-fold>
}
