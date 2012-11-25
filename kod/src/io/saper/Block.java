package io.saper;

import android.R.color;
import android.content.Context;
import android.graphics.Color;
import android.widget.Button;

public class Block extends Button
{
	private boolean isCovered;// pokazuje czy pole zosta�o odkryte
	/** pokazuje czy pole zosta�o zakryte flag� */
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
		
		// ustawianie t�a pola
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
	/** ustawia liczb� min dooko�a pola */
	public void setMinesSurrounding(int n)
	{
		numberOfMinesSurrounding = n;
	}
	/** sprawdza czy pole jest zakryte flag� */
	public boolean isFlagged()
	{
		return isFlagged;
	}
	/** ustawia flag� na polu */
	public void setFlagged(boolean b)
	{
		isFlagged = b;
	}
	/** ustawia ikonk� flagi */
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
