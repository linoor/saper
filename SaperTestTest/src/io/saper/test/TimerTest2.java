//zegar
package io.saper.test;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class TimerTest2 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public TimerTest2()
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
		// sprawdzamy czy zegar ruszy³ i prawid³owo wskazuje czas
		solo.enterText(0,"test");
		solo.clickOnButton(0);
		Random rand = new Random();
		TextView zegar = (TextView) solo.getView(R.id.timer);
		Block block = (Block) solo.getButton(rand.nextInt(9*9));
		solo.clickOnView(block);
		solo.sleep(100000);
		String co_licznik=(zegar.getText()).toString();
		Assert.assertEquals("100", co_licznik);
	}

}
