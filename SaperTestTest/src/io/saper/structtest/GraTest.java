package io.saper.structtest;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.DaneGraczy;
import io.saper.Gra;
import io.saper.MainActivity;
import io.saper.Plansza;
import io.saper.R;
//import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageButton;

public class GraTest extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public GraTest()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testGra()
	{
		solo.enterText(0, "jakasnazwa");
		solo.clickOnButton(0);
		Gra g = Gra.getInstance();
		solo.clickOnButton(0);
		assertEquals(false, g.checkWin());
		g.setMinesToFind(23);
		assertEquals(23, g.getMinesToFind());
		g.setMinesTotal(87);
		assertEquals(87, g.getMinesTotal());
		g.setAreMinesSet(false);
		assertEquals(false, g.isAreMinesSet());
		g.setAreMinesSet(true);
		assertEquals(true, g.isAreMinesSet());
	}
}
