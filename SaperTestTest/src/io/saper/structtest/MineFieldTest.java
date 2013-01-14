package io.saper.structtest;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.DaneGraczy;
import io.saper.Gra;
import io.saper.MainActivity;
import io.saper.MineField;
import io.saper.Plansza;
import io.saper.R;
//import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageButton;

public class MineFieldTest extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public MineFieldTest()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testMineField()
	{
		solo.enterText(0, "jakasnazwa");
		solo.clickOnButton(0);
		solo.sleep(2000);
		MineField g = MineField.getInstance();
		g.setMines(7, 4);
		//sprawdze, czy na tym polu nie ma miny!
		int nr = g.kolumny()*(7 - 1) + (4-1);
		Block b = (Block) solo.getButton(nr);
		assertEquals(false, b.isMined());
		//teraz policzy miny
		int ileMin=0;
		for(int i=0; i<g.kolumny()*g.rzedy(); i++)
		{
			b = (Block) solo.getButton(i);
			if(b.isMined())ileMin++;
		}
		assertEquals(10, ileMin);
		g.ustaw(45, 32);
		assertEquals(45, g.kolumny());
		assertEquals(32, g.rzedy());
	}
}
