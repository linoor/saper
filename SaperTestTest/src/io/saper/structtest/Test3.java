package io.saper.structtest;

import java.util.Random;

import io.saper.Block;
import io.saper.MainActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.jayway.android.robotium.solo.Solo;

import junit.framework.Assert;
import junit.framework.TestCase;

public class Test3 extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public Test3()
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
			//tu blad wyskakuje - moze jednak zwykle klikanie
			//getActivity().ustaw(columns,rows,mines);
			//getActivity().startNewGame();
			solo.clickOnImage(0);
			solo.clickOnButton(nr);
			//po kliknieciu klawisza - szuka zaminowango pola, klika na nie
			//sprawdzamy, czy gra jest przegrana!
			nr = rand.nextInt(getActivity().kolumny()*getActivity().rzedy());
			solo.clickOnButton(nr);
			int r=1,k=1;//numer rzedu i kolumny
			boolean czyDalej = true;
			while(czyDalej)
			{
				r=rand.nextInt(getActivity().rzedy());
				k=rand.nextInt(getActivity().kolumny());
				if(r<1)r=1;
				if(k<1)k=1;
				Block b = getActivity().getBlocks()[r][k];
				if(b.isMined())czyDalej=false;
			}
			solo.clickOnButton((r-1)*getActivity().kolumny()+k-1);
			//test checkWin - powinno zwrociæ false, bo nie wygralismy!
			Assert.assertEquals(false,getActivity().checkWin());
			//czy zmienna odpowiedzialna za koniec gry odpowiednio ustawiona?
			Assert.assertEquals(true,getActivity().isGameOver());
			//czy czas sie zatrzymal
			Assert.assertEquals(false,getActivity().isTimerstarted());
			//gra jeszcze nie rozpoczêta, miny dalej ustawione
			Assert.assertEquals(true,getActivity().isAreMinesSet());
			//klawisze powinny byæ nieklikalne!
			for(k=1;k<=rows;k++)
				for(int j=1;j<=columns;j++)
				{
					Block b = getActivity().getBlocks()[k][j];
					Assert.assertEquals(false, b.isClickable());
					Assert.assertEquals(false, b.isLongClickable());
				}
		}
	}

}
