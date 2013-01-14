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
import io.saper.Zegar;
//import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageButton;

public class ZegarTest extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public ZegarTest()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	@Override
	   public void tearDown() throws Exception {
	        solo.finishOpenedActivities();
	  }
	
	public void testMineField()
	{
		DaneGraczy d = DaneGraczy.getInstance();
		if(d.getNazwaGracza()=="")solo.enterText(0, "jakasnazwa");
		solo.clickOnButton(0);
		Zegar z = Zegar.getInstance();
		z.setTimerstarted(true);
		assertEquals(true, z.isTimerstarted());
		z.setTimerstarted(false);
		assertEquals(false, z.isTimerstarted());
		z.setTimerstarted(true);
		assertEquals(true, z.isTimerstarted());
		z.setTimerstarted(false);
		assertEquals(false, z.isTimerstarted());
		z.setCzas(878);
		assertEquals(878, z.getCzas());
		z.setCzas(43);
		assertEquals(43, z.getCzas());
	}
}
