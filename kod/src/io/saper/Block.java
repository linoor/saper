package io.saper;

import android.content.Context;
import android.widget.Button;

public class Block extends Button
{
	private boolean isCovered; // pokazuje czy pole zosta�o odkryte
	private boolean isMined; // pokazuje czy blok posiada pod spodem mine
	private int numberOfMinesSurrounding; // pokazuje liczbe min w poblizu pola
	
	public Block(Context context)
	{
		super(context);
		isCovered = true;
		isMined = false;
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
}
