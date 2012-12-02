package io.saper.test;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
import android.test.ActivityInstrumentationTestCase2;

public class FlagTest extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public FlagTest()
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
		Random rand = new Random();
		// klika na dziesiec losowych punktow i zaznacza na nich flagi
		for(int i = 0; i < 10; i++)
		{
			Block block = (Block) solo.getButton(rand.nextInt(9*9));
			if(block.isFlagged())
			{
				i--;
				continue;
			}
			solo.clickLongOnView(block);
			// sprawdza czy pole "isFlagged" zaznaczonego wlasnie bloku jest ustawione na "true"
			Assert.assertTrue(block.isFlagged());
			// sprawdza czy mozna klikn¹æ pole ( powinno siê nie daæ )
			Assert.assertTrue(block.isCovered());
		}
			
	}

}
