//zegar
package io.saper.test;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.MainActivity;
import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class TimerTest3 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public TimerTest3()
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
		// sprawdzamy czy zegar nie ruszy³
		TextView zegar = (TextView) solo.getView(R.id.timer);
		String co_licznik=(zegar.getText()).toString();
		Assert.assertEquals("000", co_licznik);
	}

}
