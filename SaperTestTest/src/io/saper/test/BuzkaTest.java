package io.saper.test;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
import io.saper.R;
//import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageButton;

public class BuzkaTest extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public BuzkaTest()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testApp()
	{
		solo.assertCurrentActivity("Sprawdzanie czy dobra aplikacja jest w³¹czona", MainActivity.class);
	}
	
	public void testShortClick()
	{
		ImageButton buzka = (ImageButton) solo.getView(R.id.imageButton1);
		solo.clickOnView(buzka);
		//i co z tym zrobic???
	}
	
	

}
