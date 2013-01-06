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
		// klikamy na iles przyciskow
		boolean ok=true;//czy wszystkie klikniete nieodkryte zostaly odkryte
		for(int i=0;i<71;i++)
		{
			boolean zrobiono=false;
			int nr = 0;
			Block klawisz = null;
			Random rand = new Random();
			while(!zrobiono)
			{
				nr=rand.nextInt(81);
				klawisz = (Block) solo.getButton(nr);
				if(klawisz.isCovered() && !klawisz.isMined())zrobiono=true;
			}
			solo.clickOnButton(nr);
			/*if(solo.waitForText("M", 1, 20))ok=false;
			Assert.assertTrue(ok);*/
		}
		Assert.assertTrue(getActivity().checkWin());
	}		
		
		
}
