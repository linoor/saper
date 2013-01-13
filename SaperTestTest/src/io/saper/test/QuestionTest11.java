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

public class QuestionTest11 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public QuestionTest11()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testQuestion2()
	{
		Random r = new Random();
		for(int i = 0; i < 10; i++)
		{
			//solo.clickOnEditText(0);
			if(i!=0)
			{
				solo.clickOnImage(0);
			}
			else solo.enterText(0,"testy");
			solo.clickOnButton(0);
			int rzedy = getActivity().rzedy();
			int kolumny = getActivity().kolumny();
			int nr = 8;
			Block block = (Block) solo.getButton(nr);
			solo.clickLongOnView(block);
			String s = (block.getText()).toString();
			Assert.assertEquals("F", s);
			solo.clickLongOnView(block);
			s = (block.getText()).toString();
			Assert.assertEquals("?", s);
			solo.clickLongOnView(block);
			s = (block.getText()).toString();
			Assert.assertEquals("", s);
		}
	}

}
