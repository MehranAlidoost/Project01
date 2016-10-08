package com.testapp.oneapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.testapp.oneapp.model.AllArray;

/**
 * Created by Mehran-A on 10/8/2016.
 */
public class PatientStatusListAdapter extends BaseAdapter
{
	Context  context;
	AllArray patientStatusListItemsArray;

	public PatientStatusListAdapter (AllArray patientStatusListItemsArray, Context context)
	{
		this.patientStatusListItemsArray = patientStatusListItemsArray;
		this.context = context;
	}

	@Override
	public int getCount ()
	{
		return patientStatusListItemsArray.getPatientStatusListItems ().size ();
	}

	@Override
	public Object getItem (int i)
	{
		return patientStatusListItemsArray.getPatientStatusListItems ().get (i);
	}

	@Override
	public long getItemId (int i)
	{
		return i;
	}

	@Override
	public View getView (int i, View view, ViewGroup viewGroup)
	{
		View     view1    = LayoutInflater.from (context).inflate (R.layout.patientstatusactivity_listview, null);
		TextView textView = (TextView) view1.findViewById (R.id.patientStatusActivitylistViewEditText);
		textView.setText (patientStatusListItemsArray.getPatientStatusListItems ().get (i).getItem ());

		return view1;
	}
}
