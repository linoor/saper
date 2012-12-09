package io.saper.test;

import com.jayway.android.robotium.solo.Solo;

import io.saper.MainActivity;
import android.test.ActivityInstrumentationTestCase2;
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
		
	}

}
