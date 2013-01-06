package io.saper.test;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class FlagTest2 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public FlagTest2()
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
		solo.enterText(0,"test");
		solo.clickOnButton(0);
		// klika na 5 losowych punktow i zaznacza na nich flagi
		for(int i = 0; i < 5; i++)
		{
			Block block = (Block) solo.getButton(rand.nextInt(9*9));
			if(block.isFlagged())
			{
				i--;
				continue;
			}
			solo.clickLongOnView(block);
		}
		TextView licznik = (TextView) solo.getView(R.id.licznik_min);
		// pola powinno zostac oflagowane
		boolean oflagowany = solo.waitForText("F", 5, 20);
		String co_licznik=(licznik.getText()).toString();
		Assert.assertEquals("005", co_licznik);
		Assert.assertEquals(true, oflagowany);
			
	}

}
