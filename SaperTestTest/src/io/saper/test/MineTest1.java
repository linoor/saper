//zegar
package io.saper.test;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
//import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
//import android.widget.TextView;

public class MineTest1 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public MineTest1()
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
		Random rand = new Random();
		int nr = rand.nextInt(81);
		solo.enterText(0,"test");
		solo.clickOnButton(0);
		Block block = (Block) solo.getButton(nr);
		solo.clickOnView(block);
		boolean zaminowany = block.isMined();
		boolean odminowany = solo.waitForText("M", 1, 20);
		Assert.assertEquals(zaminowany,odminowany);
	}

}
