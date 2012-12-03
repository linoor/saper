//zegar
package io.saper.test;

//import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
//import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
//import android.widget.TextView;

public class MineTest3 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public MineTest3()
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
	
	public void testTimer() throws InterruptedException
	{
		Block block = (Block) solo.getButton(80);
		solo.clickOnView(block);
		boolean zaminowany = block.isMined();
		boolean odminowany = solo.waitForText("M", 1, 20);
		Assert.assertEquals(zaminowany, odminowany);
	
	}

}
