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
	private boolean isQuestionMark;
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
		this.setBackgroundResource(R.drawable.orange);
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
		this.setBackgroundResource(R.drawable.red);
		ustawLiczby();
	}
	/**funkcja ustawiajaca numerki na polach
	 * 
	 */
	public void ustawLiczby()
	{
		
		final int liczbaMin = getMinesSurrounding();
		setText(String.valueOf(liczbaMin));
		if(liczbaMin == 0)
		{
			setText("");
		}
		
		switch(liczbaMin)
		{
		case 1:
			setTextColor(Color.WHITE);
			break;
		case 2:
			setTextColor(Color.LTGRAY);
			break;
		case 3:
			setTextColor(Color.BLUE);
			break;
		case 4:
			setTextColor(Color.GREEN);
			break;
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			setTextColor(Color.WHITE);
			break;
			
		}
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
	public int getMinesSurrounding()
	{
		return numberOfMinesSurrounding;
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
			isFlagged = true;
		}
		else
		{
			setText("");
			isFlagged = false;
		}
		
	}
	/** Ustawia ikonkê miny */
	public void setMineIcon()
	{
		setText("M");
		setTextColor(Color.RED);
	}
	/** sprawdza czy pole jest zakryte pytajnikiem */
	public boolean isQuestionMark()
	{
		return isQuestionMark;
	}
	/** ustawia pytajnik na polu */
	public void setQuestionMark(boolean b)
	{
		isQuestionMark = b;
	}
	/** ustawia ikonkê pytajnika */
	public void setQuestionMarkIcon(boolean b)
	{
		if(b)
		{
			setText("?");
			isQuestionMark = true;
		}
		else
		{
			setText("");
			isQuestionMark = false;
		}
		
	}
}
