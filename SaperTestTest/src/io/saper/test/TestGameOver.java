package io.saper.test;

import java.util.Random;

import junit.framework.Assert;

import io.saper.Block;
import io.saper.MainActivity;

import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;

public class TestGameOver extends ActivityInstrumentationTestCase2<MainActivity>
{
	private Solo solo;
	private Block blocks[];
	
	public TestGameOver()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo.enterText(0,"test");
		solo.clickOnButton(0);
		solo = new Solo(getInstrumentation(), getActivity());
		// rozpoczynamy grê i klikamy na jakies pola
		Random rand = new Random();
		blocks[0] = (Block) solo.getButton(rand.nextInt(9*9));
		solo.clickOnView(blocks[0]);
		for(int i = 1; i < 2; i++)
		{
			blocks[i] = new Block(null);
			blocks[i] = (Block) solo.getButton(rand.nextInt(9*9));
			if(!blocks[i].isMined())solo.clickOnView(blocks[i]);
			else i--;
		}
	}
	
	public void testGameOver()
	{
		Assert.assertEquals(true, getActivity().isGameOver());
	}
	
	
	

}
