package io.saper.test;

//import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
//import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
//import android.widget.TextView;

public class FlagTest8 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public FlagTest8()
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
		solo.assertCurrentActivity("Sprawdzanie czy dobra aplikacja jest w��czona", MainActivity.class);
	}
	
	public void testFlags()
	{
		//boolean oflagowany=true;
		//Random rand = new Random();
		Block block = null;
		// losuje punkt
		block = (Block) solo.getButton(0);
		//klika dlugo
		solo.clickLongOnView(block);
		//sprawdzi, czy pole oznaczone flag�
		Assert.assertEquals(true, block.isFlagged());
			
	}

}
