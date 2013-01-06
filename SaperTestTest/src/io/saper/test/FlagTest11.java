package io.saper.test;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
//import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
//import android.widget.TextView;
import android.widget.TextView;

public class FlagTest11 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public FlagTest11()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testApp()
	{
		solo.assertCurrentActivity("Sprawdzanie czy dobra aplikacja jest w³¹czona", MainActivity.class);
	}
	
	public void testFlags()
	{
		boolean oflagowany=true;
		Block block = null;
		for(int i=0;i<2;i++)
		{
			if(i!=0)solo.clickOnImage(0);
			solo.enterText(0,"test");
			solo.clickOnButton(0);
			oflagowany=true;
			while(oflagowany)
			{
				Random rand = new Random();
				int nr = rand.nextInt(9*9);
				// losuje punkt
				block = (Block) solo.getButton(nr);
				oflagowany=block.isFlagged();
			}
			//klika dlugo
			solo.clickLongOnView(block);
			//sprawdzi, czy pole oznaczone flag¹
			Assert.assertEquals(true, block.isFlagged());
		}
			
	}

}
