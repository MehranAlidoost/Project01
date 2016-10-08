package com.testapp.oneapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.testapp.oneapp.model.AllArray;
import com.testapp.oneapp.model.PatientStatusListItem;

public class PatientStatusActivity extends AppCompatActivity
{

	ListView listView;
	AllArray patientStatusListItemsArray;

	@Override
	protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		setContentView (R.layout.activity_patient_status);
		getSupportActionBar ().setDisplayHomeAsUpEnabled (true);
		getSupportActionBar ().setTitle (getString (R.string.en_patient));
		getSupportActionBar ().setSubtitle (getString (R.string.en_status));

		patientStatusListItemsArray = new AllArray ("patient");
		patientStatusListItemsArray.addToPatientStatusListItems (new PatientStatusListItem ("Item1"));
		patientStatusListItemsArray.addToPatientStatusListItems (new PatientStatusListItem ("Item2"));
		patientStatusListItemsArray.addToPatientStatusListItems (new PatientStatusListItem ("Item3"));
		patientStatusListItemsArray.addToPatientStatusListItems (new PatientStatusListItem ("Item4"));
		patientStatusListItemsArray.addToPatientStatusListItems (new PatientStatusListItem ("Item5"));

		listView = (ListView) findViewById (R.id.patientStatusListView);
		PatientStatusListAdapter patientStatusListAdapter = new PatientStatusListAdapter (patientStatusListItemsArray, PatientStatusActivity.this);
		listView.setAdapter (patientStatusListAdapter);

	}

	@Override
	public boolean onOptionsItemSelected (MenuItem item)
	{
		this.finish ();
		return true;
	}
}
