package io.saper.structtest;

import java.util.Random;

import junit.framework.Assert;

import com.jayway.android.robotium.solo.Solo;

import io.saper.Block;
import io.saper.MainActivity;
import io.saper.R;
//import io.saper.R;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageButton;

public class BlockTest extends ActivityInstrumentationTestCase2<MainActivity>
{

	private Solo solo;
	
	public BlockTest()
	{
		super("io.saper", MainActivity.class);
	}

	protected void setUp() throws Exception
	{
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testBlock()
	{
		solo.enterText(0, "jakasnazwa");
		solo.clickOnButton(0);
		Random r = new Random();
		Block b = null;
		int nr;
		do
		{
			nr= r.nextInt(81);
			b = (Block) solo.getButton(nr);
		}while(b.isMined());
		assertEquals(true, b.isCovered());
		b.setMine();
		assertEquals(true, b.isMined());
		b.setMinesSurrounding(nr);
		assertEquals(nr, b.getMinesSurrounding());
		b.setFlagged(true);
		assertEquals(true, b.isFlagged());
		b.setFlagged(false);
		assertEquals(false, b.isFlagged());
		b.setQuestionMark(true);
		assertEquals(true, b.isQuestionMark());
		b.setQuestionMark(false);
		assertEquals(false, b.isQuestionMark());
		do
		{
			nr= r.nextInt(81);
			b = (Block) solo.getButton(nr);
		}while(b.isMined());
		assertEquals(true, b.isCovered());
		//b.uncover();
		//assertEquals(false, b.isCovered());		
	}
}
