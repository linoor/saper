package io.saper.sprint5;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
import io.saper.R;
//import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageButton;

public class testCase117 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public testCase117()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testNumerow()
	{
		solo.enterText(0, "jakasnazwa");
		solo.clickOnButton(0);
		Random r = new Random();
		int nr = r.nextInt(81);
		Block block = (Block) solo.getButton(nr);
		solo.clickLongOnView(block);
		String s = (block.getText()).toString();
		Assert.assertEquals("F", s);
		solo.clickOnView(block);
		s = (block.getText()).toString();
		Assert.assertEquals("F", s);
	}
}
