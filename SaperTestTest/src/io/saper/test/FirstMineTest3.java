package io.saper.test;

import java.util.Random;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import junit.framework.Assert;
import junit.framework.TestCase;

public class FirstMineTest3 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public FirstMineTest3()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testFirstMine3()
	{
		solo.enterText(0,"test");
		solo.clickOnButton(0);
		for(int i = 0; i < 10; i++)
		{
			int k = 72;
			solo.clickOnButton(k);
			Block block = (Block) solo.getButton(k);
			Assert.assertEquals(false, block.isMined());
			solo.clickOnImage(0);
			//solo.enterText(0,"test");
			solo.clickOnButton(0);
		}
	}

}
