package io.saper.test;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class FlagTest6 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public FlagTest6()
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
		Block block = null;
		solo.enterText(0,"test");
		solo.clickOnButton(0);
		// klika na dziesiec losowych punktow i zaznacza na nich flagi
		for(int i = 0; i < 10; i++)
		{
			block = (Block) solo.getButton(rand.nextInt(9*9));
			if(block.isFlagged())
			{
				i--;
				continue;
			}
			solo.clickLongOnView(block);
		}
		TextView licznik = (TextView) solo.getView(R.id.licznik_min);
		//naacisnal 10 - powinno byc 000 na liczniku
		String co_licznik=(licznik.getText()).toString();
		Assert.assertEquals("000", co_licznik);
		Assert.assertEquals(true,solo.waitForText("F", 10, 20));
		solo.clickLongOnView(block);
		//odcisnal jedno - powinno byc 001 na liczniku
		co_licznik=(licznik.getText()).toString();
		Assert.assertEquals("001", co_licznik);
		Assert.assertEquals(true,solo.waitForText("F", 9, 20));
	}

}
