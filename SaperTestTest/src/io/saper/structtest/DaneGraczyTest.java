package io.saper.structtest;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.DaneGraczy;
import io.saper.MainActivity;
import io.saper.Plansza;
import io.saper.R;
//import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageButton;

public class DaneGraczyTest extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public DaneGraczyTest()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testDaneGraczy()
	{
		solo.enterText(0, "jakasnazwa");
		solo.clickOnButton(0);
		DaneGraczy d = DaneGraczy.getInstance();
		String nazwa = "nowyyyT";
		String czas = "89";
		d.setNazwaGracza(nazwa);
		assertEquals(nazwa, d.getNazwaGracza());
		d.setPlansza(Plansza.Max);
		assertEquals(Plansza.Max, d.getPlansza());
		d.setPlansza(Plansza.Med);
		assertEquals(Plansza.Med, d.getPlansza());
		d.setPlansza(Plansza.Min);
		assertEquals(Plansza.Min, d.getPlansza());
		assertEquals(0,d.getIleRozegranych());
		assertEquals(0,d.getIleWygranych());
		d.update(czas, false);
		assertEquals("Brak twojego najlepszego wyniku na tej planszy",
				d.najlepszyWynik());
		assertEquals(1,d.getIleRozegranych());
		assertEquals(0,d.getIleWygranych());
		d.update(czas, true);
		assertEquals("Twój najlepszy wynik na tej planszy to "+czas+" s",
				d.najlepszyWynik());
		assertEquals(2,d.getIleRozegranych());
		assertEquals(1,d.getIleWygranych());
	}
}
