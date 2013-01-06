package io.saper.test;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.Gra;
import io.saper.MainActivity;
import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;

public class ShortClickTest extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public ShortClickTest()
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
		solo.enterText(0,"test");
		solo.clickOnButton(0);
		Random rand = new Random();
		for(int i = 0; i < 10; i++)
		{
			Block block = (Block) solo.getButton(rand.nextInt(9*9));
			solo.clickOnView(block);
			// pole powinno zostac odkryte
			Assert.assertEquals(false, block.isCovered());
		}
	}
	
	

}
