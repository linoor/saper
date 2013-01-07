package io.saper.test;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
import android.test.ActivityInstrumentationTestCase2;

public class PrzykladowaGra extends
		ActivityInstrumentationTestCase2<MainActivity> {
	
	private Solo solo;

	public PrzykladowaGra() {
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testGra() throws InterruptedException
	{
		solo.enterText(0,"test");
		solo.clickOnButton(0);
		Random rand = new Random();
		// klikamy na iles przyciskow
		int nr0 = rand.nextInt(81);
		Block klawisz = (Block) solo.getButton(nr0);
		solo.clickOnButton(nr0);
		for(int i=0;i<81;i++)
		{
			klawisz = (Block) solo.getButton(i);
			if(klawisz.isCovered() && !klawisz.isMined())solo.clickOnButton(i);
			/*if(solo.waitForText("M", 1, 20))ok=false;
			Assert.assertTrue(ok);*/
		}
		Assert.assertTrue(getActivity().checkWin());
	}		
		
		
}
