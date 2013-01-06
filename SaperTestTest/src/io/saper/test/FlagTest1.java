package io.saper.test;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class FlagTest1 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public FlagTest1()
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
		solo.enterText(0,"test");
		solo.clickOnButton(0);
		boolean oflagowany=true;
		boolean zaminowany=false;
		Random rand = new Random();
		Block block = null;
		// losuje punkt
		while(oflagowany){
			block = (Block) solo.getButton(rand.nextInt(9*9));
			oflagowany=block.isFlagged();
		}
		//klika krótko
		solo.clickOnView(block);
		zaminowany=block.isMined();
		boolean odminowany = solo.waitForText("M", 1, 20);
		//sprawdzi, czy pole licznika wskazuje teraz 000
		TextView licznik = (TextView) solo.getView(R.id.licznik_min);
		String co_licznik=(licznik.getText()).toString();
		Assert.assertEquals("010", co_licznik);
		Assert.assertEquals(zaminowany, odminowany);
			
	}

}
