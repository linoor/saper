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
		solo = new Solo(getInstrumentation(), getActivity());
		
		
		// rozpoczynamy grê i klikamy na jakies pola
		Random rand = new Random();
		for(int i = 0; i < 10; i++)
		{
			blocks[i] = new Block(null);
			blocks[i] = (Block) solo.getButton(rand.nextInt(9*9));
			solo.clickOnView(blocks[i]);
		}
	}
	
	public void testGameOver()
	{
		Assert.assertEquals(true, getActivity().isGameOver());
	}
	
	
	

}
