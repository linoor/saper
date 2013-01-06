package io.saper.test;

import java.util.Random;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
import android.test.ActivityInstrumentationTestCase2;

public class KlawiszeTest extends
		ActivityInstrumentationTestCase2<MainActivity> {
	
	private Solo solo;

	public KlawiszeTest() {
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testKlawisze() throws InterruptedException
	{
		// klikamy na iles przyciskow
		boolean ok=true;//czy wszystkie klikniete nieodkryte zostaly odkryte
		solo.enterText(0,"test");
		solo.clickOnButton(0);
		for(int i=0;i<30;i++)
		{
			boolean zrobiono=false;
			int nr = 0;
			Block klawisz = null;
			Random rand = new Random();
			while(!zrobiono)
			{
				nr=rand.nextInt(81);
				klawisz = (Block) solo.getButton(nr);
				if(klawisz.isCovered())zrobiono=true;
			}
			solo.clickOnButton(nr);
			ok=ok&&(!klawisz.isCovered());
		}
		assert(ok);
	
	}
	//generuje klikniecie na losowy klawisz i zwraca jego stan
		/*private boolean kliknijLos()
		{
			// klikamy na wszystkie przyciski
			int nr;
			Block klawisz = null;
			Random rand = new Random();
			do{
				nr = rand.nextInt(81);
				klawisz = (Block) solo.getButton(nr);
			}while(klawisz.isCovered());//tak dlugo losujemy, az trafi na nieodkryty
			solo.clickOnButton(nr);
		    // sprawdzamy czy ten klawisz odkryty
			return !klawisz.isCovered();//zwroc, czy klawisz odkryty
		}*/
		
		public void testApp()
		{
			solo.assertCurrentActivity("Sprawdzanie czy dobra aplikacja jest w³¹czona", MainActivity.class);
		}
		
		/*public void testTimer() throws InterruptedException
		{
			// klikamy na jakis przycisk
			solo.clickOnButton(21);
			// sprawdzamy czy zegar ruszy³
			assertTrue(solo.waitForText("001", 1, 3));
		
		}*/
		
		//czy klawisz klikniêty
		public void testKlawisz() throws InterruptedException
		{
			// klikamy na iles przyciskow
			boolean ok=true;//czy wszystkie klikniete nieodkryte zostaly odkryte
			int nr;
			Block klawisz = null;
			Random rand = new Random();
			nr = rand.nextInt(81);
			klawisz = (Block) solo.getButton(nr);
			solo.clickOnButton(nr);
			ok=ok&&(!klawisz.isCovered());
			assert(ok);
		
		}
		
		//czy klawisze odkryte
		
		/*public void testPola() throws InterruptedException
		{
			// klikamy na wszystkie przyciski
			for(int i=0;i<4;i++)
				solo.clickOnButton(i);
			// sprawdzamy czy wszystkie odkryte
			assertTrue(solo.waitForText("", 81, 2));
		
		}*/
		
		/*public void testPola2() throws InterruptedException
		{
			// klikamy na wszystkie przyciski
			for(int i=0;i<81;i++)
				solo.clickOnButton(i);
			// sprawdzamy czy 10 min!
			assertTrue(solo.waitForText("M", 10, 2));
		
		}*/


}
