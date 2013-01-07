package io.saper.structtest;

import java.util.Random;

import io.saper.Block;
import io.saper.MainActivity;
import android.test.ActivityInstrumentationTestCase2;

import com.jayway.android.robotium.solo.Solo;

import junit.framework.Assert;
import junit.framework.TestCase;

public class RippleTest1 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public RippleTest1()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testGame()
	{
		solo.enterText(0,"test");
		solo.clickOnButton(0);
		Random rand = new Random();
		int nr = rand.nextInt(9*9);
		Block block = (Block) solo.getButton(nr);
		solo.clickOnView(block);
		for(int i=0;i<81;i++)
		{
			block = (Block) solo.getButton(i);
			String wynik = (block.getText()).toString();
			if(!block.isCovered())
			{
				if(block.getMinesSurrounding() != 0)
				{
					String ileMin = new Integer(block.getMinesSurrounding()).toString();
					Assert.assertEquals(ileMin, wynik);
				}
				else
				{
					Assert.assertEquals("", wynik);
				}
			}
		//solo.clickOnView(block);
		}
		
	}

}
