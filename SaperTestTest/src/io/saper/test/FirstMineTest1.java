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

public class FirstMineTest1 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public FirstMineTest1()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testFirstMine1()
	{
		for(int i = 0; i < 10; i++)
		{
			if(i!=0)
			{
				solo.clickOnImage(0);
			}
			else solo.enterText(0,"test");
			solo.clickOnButton(0);
			int k = 0;
			solo.clickOnButton(k);
			Block block = (Block) solo.getButton(k);
			Assert.assertEquals(false, block.isMined());
			solo.clickOnImage(0);
			solo.clickOnButton(0);
		}
	}

}
