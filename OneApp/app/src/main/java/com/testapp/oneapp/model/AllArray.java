package com.testapp.oneapp.model;

import java.util.ArrayList;

/**
 * Created by Mehran-A on 10/8/2016.
 */
public class AllArray
{

	ArrayList<ListMenuItem>          listMenuItems;
	ArrayList<PatientStatusListItem> patientStatusListItems;

	public AllArray (String s)
	{
		switch (s)
		{
			case "main":
				listMenuItems = new ArrayList<> ();
				break;
			case "patient":
				patientStatusListItems = new ArrayList<> ();
				break;
		}


	}

	public void addToListMenuItem (ListMenuItem listMenuItem)
	{
		this.listMenuItems.add (listMenuItem);
	}

	public ArrayList<ListMenuItem> getListMenuItems ()
	{
		return listMenuItems;
	}

	public void addToPatientStatusListItems (PatientStatusListItem patientStatusListItem)
	{
		patientStatusListItems.add (patientStatusListItem);
	}

	public ArrayList<PatientStatusListItem> getPatientStatusListItems ()
	{
		return patientStatusListItems;
	}
}
