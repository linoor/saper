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

public class FirstMineTest4 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public FirstMineTest4()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testFirstMine4()
	{
		for(int i = 0; i < 10; i++)
		{
			if(i!=0)
			{
				solo.clickOnImage(0);
			}
			else solo.enterText(0,"testy");
			solo.clickOnButton(0);
			int k = 8;
			Block block = (Block) solo.getButton(k);
			solo.clickOnButton(k);
			Assert.assertEquals(false, block.isMined());
		}
	}

}
