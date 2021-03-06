package io.saper.test;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

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
		solo.assertCurrentActivity("Sprawdzanie czy dobra aplikacja jest w��czona", MainActivity.class);
	}
	
	public void testFlags()
	{
		Random rand = new Random();
		// klika na dziesiec losowych punktow i zaznacza na nich flagi
		for(int i = 0; i < 10; i++)
		{
			if(i!=0)solo.clickOnImage(0);
			else solo.enterText(0,"test");
			solo.clickOnButton(0);
			Block block = (Block) solo.getButton(rand.nextInt(9*9));
			if(block.isFlagged())
			{
				i--;
				continue;
			}
			solo.clickLongOnView(block);
			// sprawdza czy pole "isFlagged" zaznaczonego wlasnie bloku jest ustawione na "true"
			Assert.assertTrue(block.isFlagged());
			// sprawdza czy mozna klikn�� pole ( powinno si� nie da� )
			Assert.assertTrue(block.isCovered());
		}
			
	}

}
