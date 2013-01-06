package io.saper.test;

//import java.util.Random;

import junit.framework.Assert;
//import io.saper.Block;
import io.saper.MainActivity;
import io.saper.R;

import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class Licznik1Test extends
		ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;
	
	public Licznik1Test() {
		super("io.saper",MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	public void testApp()
	{
		solo.assertCurrentActivity("Sprawdzanie czy dobra aplikacja jest w³¹czona", MainActivity.class);
	}
	
	public void testShortClick()
	{
		//Random rand = new Random();
		solo.enterText(0,"test");
		solo.clickOnButton(0);
		for(int i = 0; i < 1; i++)
		{
			TextView licznik = (TextView) solo.getView(R.id.licznik_min);
			// pole powinno zostac odkryte
			String co_licznik=(licznik.getText()).toString();
			Assert.assertEquals("010", co_licznik);
		}
	}
}
