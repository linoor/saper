package io.saper;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.widget.Button;

public class Block extends Button
{
	private boolean isCovered;// pokazuje czy pole zosta³o odkryte
	/** pokazuje czy pole zosta³o zakryte flag¹ */
	private boolean isFlagged;
	private boolean isMined; // pokazuje czy blok posiada pod spodem mine
	private int numberOfMinesSurrounding; // pokazuje liczbe min w poblizu pola
	
	public Block(Context context)
	{
		super(context);
		isCovered = true;
		isMined = false;
		isFlagged = false;
		numberOfMinesSurrounding = 0;
		
		// ustawianie t³a pola
		this.setBackgroundResource(R.drawable.tlo_pola);
	}
	/** sprawdza czy pole jest odkryte */
	public boolean isCovered()
	{
		return isCovered;
	}
	/** odkrywa pole */
	public void uncover()
	{
		if(!isCovered)
		{
			return;
		}
		isCovered = false;
		this.setBackgroundResource(R.drawable.szara);
	}
	/** ustawia mine */
	public void setMine()
	{
		isMined = true;
	}
	/** sprawdza czy pod polem jest mina */
	public boolean isMined()
	{
		return isMined;
	}
	/** ustawia liczbê min dooko³a pola */
	public void setMinesSurrounding(int n)
	{
		numberOfMinesSurrounding = n;
	}
	/** sprawdza czy pole jest zakryte flag¹ */
	public boolean isFlagged()
	{
		return isFlagged;
	}
	/** ustawia flagê na polu */
	public void setFlagged(boolean b)
	{
		isFlagged = b;
	}
	/** ustawia ikonkê flagi */
	public void setFlagIcon(boolean b)
	{
		if(b)
		{
			setText("F");
			setTextColor(Color.CYAN);
		}
		else
		{
			setText("");
		}
		
	}
}
