package com.ojvar.patientmonitoring.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ojvar.patientmonitoring.Models.MainMenuItem;
import com.ojvar.patientmonitoring.Models.MainMenuItemHolder;
import com.ojvar.patientmonitoring.R;

/**
 * Main Menu Adapter
 */
public class MainMenuAdapter extends BaseAdapter
{
	// <editor-fold defaultstate="" desc="Variables">
	Context        context;
	MainMenuItem[] items;
	LayoutInflater inflater;
	// </editor-fold>

	// <editor-fold defaultstate="" desc="Methods">

	/**
	 * Constructor
	 *
	 * @param context
	 * @param items
	 */
	public MainMenuAdapter (Context context, MainMenuItem[] items)
	{
		this.context = context;
		this.items = items;
		this.inflater = LayoutInflater.from (context);
	}

	/**
	 * Get Count
	 *
	 * @return
	 */
	@Override
	public int getCount ()
	{
		return items.length;
	}

	/**
	 * Get Item
	 *
	 * @param position
	 *
	 * @return
	 */
	@Override
	public Object getItem (int position)
	{
		return items[position];
	}

	/**
	 * Get Item Id
	 *
	 * @param position
	 *
	 * @return
	 */
	@Override
	public long getItemId (int position)
	{
		return position;
	}

	/**
	 * Get View
	 *
	 * @param position
	 * @param convertView
	 * @param parent
	 *
	 * @return
	 */
	@Override
	public View getView (int position, View convertView, ViewGroup parent)
	{
		MainMenuItemHolder holder;

		if (null == convertView)
		{
			convertView = inflater.inflate (R.layout.main_menu_item_template, parent, false);
			holder = new MainMenuItemHolder (convertView, items[position].getText (), items[position].getIcon ());
			convertView.setTag (holder);
		}
		else
		{
			holder = (MainMenuItemHolder) convertView.getTag ();

			// Set data
			holder.getTitleTextView ().setText (items[position].getText ());
			holder.getIconImageView ().setImageResource (items[position].getIcon ());
		}

		return convertView;
	}
	// </editor-fold>
}
