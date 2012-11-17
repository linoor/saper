package io.saper;

import android.content.Context;
import android.widget.Button;

public class Block extends Button
{
	private boolean isCovered; // pokazuje czy pole zosta³o odkryte
	private boolean isMined; // pokazuje czy blok posiada pod spodem mine
	private int numberOfMinesSurrounding; // pokazuje liczbe min w poblizu pola
	
	public Block(Context context)
	{
		super(context);
		isCovered = true;
		isMined = false;
		numberOfMinesSurrounding = 0;
		
		// ustawianie t³a pola
		this.setBackgroundResource(R.drawable.tlo_pola);
	}
	public boolean isCovered()
	{
		return isCovered;
	}
	public void uncover()
	{
		isCovered = true;
		this.setBackgroundResource(R.drawable.szara);
	}
}
