package io.saper.test;

import io.saper.MainActivity;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Toast;

import com.jayway.android.robotium.solo.Solo;

import junit.framework.TestCase;

public class toastTest extends ActivityInstrumentationTestCase2<MainActivity>
{
	private Solo solo;
	
	public toastTest()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testDialog()
	{
		solo.enterText(0,"test");
		solo.clickOnButton(0);
		solo.sleep(1000);
		Context context = getActivity().getApplicationContext();
		CharSequence text = "Hello Toast";
		int duration = Toast.LENGTH_SHORT;
		
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
	}
}
