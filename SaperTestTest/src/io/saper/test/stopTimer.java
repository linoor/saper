package io.saper.test;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;

public class stopTimer extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public stopTimer()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testShortClick()
	{
		solo.enterText(0,"test");
		solo.clickOnButton(0);
		Random rand = new Random();
		for(int i = 0; i < 3; i++)
		{
			Block block = (Block) solo.getButton(rand.nextInt(9*9));
			solo.clickOnView(block);
		}
		getActivity().stopTimer();
			synchronized(solo)
			{
				String s = solo.getView(R.id.timer).toString();
				try
				{
					solo.wait(1000);
				} catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				String s2 = solo.getView(R.id.timer).toString();
				Assert.assertEquals(s, s2);
			}
			
		}
	}
