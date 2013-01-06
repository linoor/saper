package io.saper.test;

import java.util.Random;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.TextView;
import junit.framework.Assert;
import junit.framework.TestCase;

public class ChoiceTest2 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public ChoiceTest2()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testChoice2() throws InterruptedException
	{
		solo.enterText(0,"test");
		solo.clickOnButton(0);
		TextView licznik = (TextView) solo.getView(R.id.licznik_min);
		solo.sleep(100000);
		int rzedy = getActivity().rzedy();
		int kolumny = getActivity().kolumny();
		// pole powinno zostac odkryte
		String co_licznik=(licznik.getText()).toString();
		Assert.assertEquals("010", co_licznik);
		Assert.assertEquals(9, rzedy);
		Assert.assertEquals(9, kolumny);
	}

}
