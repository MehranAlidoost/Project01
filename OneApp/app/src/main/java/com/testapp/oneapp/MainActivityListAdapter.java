package com.testapp.oneapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.testapp.oneapp.model.AllArray;

public class MainActivityListAdapter extends BaseAdapter
{

	Context  context;
	//	ListMenuItem[] menuItems;
	AllArray listMenuItemsArray;

	public MainActivityListAdapter (Context context, AllArray listMenuItemsArray)
	{
		this.context = context;
		this.listMenuItemsArray = listMenuItemsArray;
	}

	//	public MainActivityListAdapter (Context context, ListMenuItem[] MenuItem)
//	{
//		this.context = context;
//		this.menuItems = menuItems;
//	}

	@Override
	public int getCount ()
	{
//			return menuItems.length;
		return listMenuItemsArray.getListMenuItems ().size ();
	}

	@Override
	public Object getItem (int i)
	{
//		return menuItems[i];
		return listMenuItemsArray.getListMenuItems ().get (i);
	}

	@Override
	public long getItemId (int i)
	{
		return i;
	}

	@Override
	public View getView (int i, View view, ViewGroup viewGroup)
	{
		View      view1     = LayoutInflater.from (context).inflate (R.layout.mainactivity_listview, null);
		TextView  textView  = (TextView) view1.findViewById (R.id.mainActivitylistViewEditText);
		ImageView imageView = (ImageView) view1.findViewById (R.id.mainActivityListViewImageView);
		textView.setText (listMenuItemsArray.getListMenuItems ().get (i).getString ());
		imageView.setImageResource (listMenuItemsArray.getListMenuItems ().get (i).getAnInt ());

		return view1;
	}
}
