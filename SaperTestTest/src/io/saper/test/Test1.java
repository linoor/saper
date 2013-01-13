package io.saper.test;

import java.util.Random;

import io.saper.Block;
import io.saper.MainActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.jayway.android.robotium.solo.Solo;

import junit.framework.Assert;
import junit.framework.TestCase;

public class Test1 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public Test1()
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
			if(i!=0)solo.clickOnImage(0);
			solo.enterText(0,"test");
			solo.clickOnButton(nr);
			Assert.assertEquals(0, getActivity().getCzas());
			Assert.assertEquals(false,getActivity().isGameOver());
			Assert.assertEquals(mines,getActivity().getMinesToFind());
			Assert.assertEquals(mines,getActivity().getMinesTotal());
			Assert.assertEquals(columns,getActivity().kolumny());
			Assert.assertEquals(rows,getActivity().rzedy());
			Assert.assertEquals(false,getActivity().isTimerstarted());
			Assert.assertEquals(false,getActivity().isAreMinesSet());
			for(int j=1;j<=rows;j++)
				for(int k=1;k<=columns;k++)
				{
					Block b = getActivity().getBlocks()[j][k];
					Assert.assertEquals(true,b.isCovered());
					Assert.assertEquals(false,b.isFlagged());
					Assert.assertEquals(false,b.isMined());
				}
		}
	}

}
