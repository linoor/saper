package io.saper.test;

import io.saper.Block;
import io.saper.MainActivity;
import android.test.ActivityInstrumentationTestCase2;

import com.jayway.android.robotium.solo.Solo;

import junit.framework.Assert;
import junit.framework.TestCase;

public class WinGameTest extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public WinGameTest()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testGameWin()
	{
		solo.enterText(0,"test");
		solo.clickOnButton(0);
		Block block = (Block) solo.getButton(0);
		for(int i = 1; i < 9*9; i++)
		{
			block = (Block) solo.getButton(i);
			if(!block.isMined())
			{
				solo.clickOnView(block);
			}
		}
		Assert.assertTrue(getActivity().checkWin());
	}

}
