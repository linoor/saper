package io.saper.test;

import java.util.Random;

import io.saper.Block;
import io.saper.MainActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.jayway.android.robotium.solo.Solo;

import junit.framework.Assert;
import junit.framework.TestCase;

public class Test2 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public Test2()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testGraRozpoczeta()
	{
		for(int i=0;i<10;i++)
		{
			int rows=9, columns=9, mines=10;
			Random rand = new Random();
			int nr = rand.nextInt(3);
			if(nr==0)
			{
				rows=9;
				columns=9;
				mines=10;
			}
			if(nr==1)
			{
				rows=16;
				columns=16;
				mines=40;
			}
			if(nr==2)
			{
				rows=30;
				columns=16;
				mines=99;
			}
			//tu blad wyskakuje - moze jednak zwykle klikanie
			//getActivity().ustaw(columns,rows,mines);
			//getActivity().startNewGame();
			solo.clickOnImage(0);
			solo.clickOnButton(nr);
			//po kliknieciu klawisza - czy zegar chodzi i czy miny ustawione?
			nr = rand.nextInt(columns*rows);
			solo.clickOnButton(nr);
			Assert.assertEquals(true,getActivity().isTimerstarted());
			Assert.assertEquals(true,getActivity().isAreMinesSet());
		}
	}

}
