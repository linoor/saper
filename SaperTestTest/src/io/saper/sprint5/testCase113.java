package io.saper.sprint5;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
import io.saper.R;
//import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageButton;

public class testCase113 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public testCase113()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testNumerow()
	{
		solo.enterText(0, "jakasnazwa");
		solo.clickOnButton(0);
		solo.clickOnButton(0);
		
		while(true)
		{
			int i = 3;
			Block block = (Block) solo.getButton(i);
			
			if(!block.isMined() && block.isCovered())
			{
				solo.clickOnView(block);
				break;
			}
			i++;
		}
		for(int i = 0 ; i < 20 ; i++)
		{
			Block block = (Block) solo.getButton(i);
			
			if(block.isMined() && block.isCovered())
			{
				solo.clickOnView(block);
				assertEquals(true, getActivity().isGameOver());
			}
		}
	}
}
