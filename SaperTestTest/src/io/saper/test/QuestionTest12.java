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

public class QuestionTest12 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public QuestionTest12()
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
			int nr = r.nextInt(rzedy*kolumny);
			Block block1 = (Block) solo.getButton(nr);
			Block block2 = null;
			do
			{
				nr = r.nextInt(rzedy*kolumny);
				block2 = (Block) solo.getButton(nr);
			}while(block2.isFlagged());
			solo.clickLongOnView(block1);
			solo.clickLongOnView(block2);
			String s1 = (block1.getText()).toString();
			String s2 = (block2.getText()).toString();
			Assert.assertEquals("F", s1);
			Assert.assertEquals("F", s2);
			solo.clickLongOnView(block1);
			s1 = (block1.getText()).toString();
			Assert.assertEquals("?", s1);
			solo.clickLongOnView(block2);
			s2 = (block2.getText()).toString();
			Assert.assertEquals("?", s2);
			solo.clickLongOnView(block1);
			s1 = (block1.getText()).toString();
			Assert.assertEquals("", s1);
			solo.clickLongOnView(block2);
			s2 = (block2.getText()).toString();
			Assert.assertEquals("", s2);
		}
	}

}
