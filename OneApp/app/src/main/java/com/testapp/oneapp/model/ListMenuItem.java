package com.testapp.oneapp.model;

/**
 * Created by Mehran-A on 10/7/2016.
 */
public class ListMenuItem
{
	String string;
	int    anInt;

	public ListMenuItem (String string, int anInt)
	{
		this.string = string;
		this.anInt = anInt;
	}

	public String getString ()
	{
		return string;
	}

	public int getAnInt ()
	{
		return anInt;
	}
}
