package com.testapp.oneapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.testapp.oneapp.model.ListMenuItem;

public class ListAdapter extends BaseAdapter
{

	Context        context;
	ListMenuItem[] menuItems;
	int count;

	public ListAdapter (Context context,ListMenuItem[] MenuItem)
	{
		this.context = context;
		this.menuItems = menuItems;
	}

	@Override
	public int getCount ()
	{
		try
		{
			return menuItems.length;
		}catch (Exception e){
			return 0;
		}

	}

	@Override
	public Object getItem (int i)
	{
		return menuItems[i];
	}

	@Override
	public long getItemId (int i)
	{
		return i;
	}

	@Override
	public View getView (int i, View view, ViewGroup viewGroup)
	{
		View     view1    = LayoutInflater.from (context).inflate (R.layout.mainactivity_listview, null);
		TextView textView = (TextView) view1.findViewById (R.id.mainActivitylistViewEditText);
		ImageView imageView = (ImageView) view1.findViewById (R.id.mainActivityListViewImageView);
		textView.setText (menuItems[i].getString ());
		imageView.setImageResource (menuItems[i].getAnInt ());

		return view1;
	}
}
