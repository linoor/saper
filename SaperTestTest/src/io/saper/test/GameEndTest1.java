package io.saper.test;

import java.util.Random;

import io.saper.Block;
import io.saper.MainActivity;
import android.test.ActivityInstrumentationTestCase2;

import com.jayway.android.robotium.solo.Solo;

import junit.framework.Assert;
import junit.framework.TestCase;

public class GameEndTest1 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public GameEndTest1()
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
		boolean zrobiono = false;
		while(!zrobiono)
		{
			nr = rand.nextInt(9*9);
			block = (Block) solo.getButton(nr);
			if(block.isMined())zrobiono=true;
		}
		solo.clickOnView(block);
		//gra sie skonczyla - po tylko tylu kliknieciach to porazka
		Assert.assertTrue(getActivity().isGameOver());
		Assert.assertFalse(getActivity().checkWin());
	}

}
