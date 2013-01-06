package io.saper.test;

import java.util.Random;

import junit.framework.Assert;
import io.saper.Block;
import io.saper.MainActivity;
import io.saper.R;

import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class Licznik2Test extends
		ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;
	
	public Licznik2Test() {
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
		Random rand = new Random();
		for(int i = 0; i < 1; i++)
		{
			if(i!=0)solo.clickOnImage(0);
			solo.enterText(0,"test");
			solo.clickOnButton(0);
			Block block = (Block) solo.getButton(rand.nextInt(9*9));
			solo.clickLongOnView(block);
			TextView licznik = (TextView) solo.getView(R.id.licznik_min);
			// pole powinno zostac oflagowane
			boolean oflagowany = solo.waitForText("F", 1, 20);
			String co_licznik=(licznik.getText()).toString();
			Assert.assertEquals("009", co_licznik);
			Assert.assertEquals(true, oflagowany);
		}
	}
}
