package io.saper.test;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class FlagTest3 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public FlagTest3()
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
	
	public void testFlags()
	{
		Random rand = new Random();
		int nr=0;//tu bedzie numer kliknietego pola
		Block block = null;
		// klika na 5 losowych punktowi zaznacza na nich flagi
		solo.enterText(0,"test");
		solo.clickOnButton(0);
		for(int i = 0; i < 1; i++)
		{
			nr=rand.nextInt(9*9);
			block = (Block) solo.getButton(nr);
			if(block.isFlagged())
			{
				i--;
				continue;
			}
			solo.clickLongOnView(block);
		}
		TextView licznik = (TextView) solo.getView(R.id.licznik_min);
		// pole powinno zostac odkryte
		String co_licznik=(licznik.getText()).toString();
		Assert.assertEquals("009", co_licznik);
		boolean oflagowany = solo.waitForText("F", 1, 20);
		Assert.assertEquals(true, oflagowany);
		solo.clickLongOnView(block);
		oflagowany = solo.waitForText("", 1, 20);
		co_licznik=(licznik.getText()).toString();
		Assert.assertEquals("010", co_licznik);
		Assert.assertEquals(false, oflagowany);
			
	}

}
