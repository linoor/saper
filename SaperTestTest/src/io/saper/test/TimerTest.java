//zegar
package io.saper.test;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.MainActivity;
import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class TimerTest extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public TimerTest()
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
		solo.enterText(0,"test");
		solo.clickOnButton(0);
		Random rand = new Random();
		int nr = rand.nextInt(81);
		TextView zegar = (TextView) solo.getView(R.id.timer);
		// klikamy na jakis przycisk
		solo.clickOnButton(nr);
		solo.sleep(1000);
		// sprawdzamy czy zegar ruszy³
		String co_zegar=(zegar.getText()).toString();
		Assert.assertEquals("001", co_zegar);
	
	}

}
