package io.saper.test;

import java.util.Random;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
import android.test.ActivityInstrumentationTestCase2;
import junit.framework.Assert;
import junit.framework.TestCase;

public class FirstMineTest extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public FirstMineTest()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testFirstMine()
	{
		Random rand = new Random();
		for(int i = 0; i < 100; i++)
		{
			int k = rand.nextInt(9*9);
			solo.clickOnButton(k);
			solo.clickOnImage(0);
			Block block = (Block) solo.getButton(k);
			Assert.assertEquals(false, block.isMined());
		}
	}

}
