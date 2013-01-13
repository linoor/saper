package io.saper.sprint4;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
import io.saper.R;
//import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageButton;

public class testCase71 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public testCase71()
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
		Random rand = new Random(36);
		solo.clickOnButton(rand.nextInt(81));
		
		
		for(int i = 0; i < 3; i++)
		{
			int random = rand.nextInt(81);
			Block block = (Block) solo.getButton(random);
			
			if(block.isMined())
			{
				i--;
				continue;
			}
			else
			{
				for(int j = 0 ; j < 3; j++)
				{
					for(int k = 0; k < 3; k++)
					{
						
					}
				}
			}
		}
	}
}
