package io.saper.test;

import java.util.Random;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import junit.framework.Assert;
import junit.framework.TestCase;

public class FirstMineTest extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public FirstMineTest()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testFirstMine()
	{
		//solo.clickOnEditText(0);
		solo.enterText(0,"test");
		solo.clickOnButton(0);
		Random rand = new Random();
		for(int i = 0; i < 10; i++)
		{
			int rzedy=getActivity().rzedy();
			int kolumny=getActivity().kolumny();
			int k = rand.nextInt(rzedy*kolumny);
			solo.clickOnButton(k);
			Block block = (Block) solo.getButton(k);
			Assert.assertEquals(false, block.isMined());
			solo.clickOnImage(0);
			solo.enterText(0,"test");
			int butt = rand.nextInt(3);
			solo.clickOnButton(butt);
		}
	}

}
