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

public class ChoiceTest4 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public ChoiceTest4()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testChoice4() throws InterruptedException
	{
		solo.clickOnImage(0);
		solo.clickOnButton(1);
		TextView licznik = (TextView) solo.getView(R.id.licznik_min);
		solo.sleep(100000);
		int rzedy = getActivity().rzedy();
		int kolumny = getActivity().kolumny();
		// pole powinno zostac odkryte
		String co_licznik=(licznik.getText()).toString();
		Assert.assertEquals("040", co_licznik);
		Assert.assertEquals(16, rzedy);
		Assert.assertEquals(16, kolumny);
	}

}
