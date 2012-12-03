package io.saper.test;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
//import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
//import android.widget.TextView;

public class FlagTest7 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public FlagTest7()
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
		Random rand = new Random();
		Block block = null;
		// losuje punkt
		while(oflagowany){
			block = (Block) solo.getButton(rand.nextInt(9*9));
			oflagowany=block.isFlagged();
		}
		//klika dlugo
		solo.clickLongOnView(block);
		//sprawdzi, czy pole oznaczone flag¹
		Assert.assertEquals(true, block.isFlagged());
			
	}

}
