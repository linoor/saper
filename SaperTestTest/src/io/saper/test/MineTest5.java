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

public class MineTest5 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public MineTest5()
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
		//Random rand = new Random();
		//int nr = rand.nextInt(81);
		//TextView zegar = (TextView) solo.getView(R.id.timer);
		// klikamy na jakis przycisk
		Block block = (Block) solo.getButton(8);
		boolean zaminowany = block.isMined();
		solo.clickOnView(block);
		boolean odminowany = solo.waitForText("M", 1, 20);
		//solo.sleep(1000);
		// sprawdzamy czy zegar ruszy³
		//String co_zegar=(zegar.getText()).toString();
		//Assert.assertEquals("001", co_zegar);
		//Assert.assertEquals(true, block.isCovered());
		Assert.assertEquals(zaminowany, odminowany);
	
	}

}
