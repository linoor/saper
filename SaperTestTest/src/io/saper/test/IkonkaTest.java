package io.saper.test;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.MainActivity;
import android.test.ActivityInstrumentationTestCase2;

public class IkonkaTest extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public IkonkaTest()
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
	
	public void testTimer() throws InterruptedException
	{
		// klikamy na jakis przycisk
		solo.clickOnButton(5);
		// sprawdzamy czy zegar ruszy³
		solo.waitForText("001", 1, 3);
	
	}

}
