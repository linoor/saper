package io.saper;

import android.widget.TextView;

public class LicznikMin {
	private static volatile LicznikMin Instance;
	private TextView minecount;
	private LicznikMin(){}
	public void init(TextView minecount)
	{
		this.minecount = minecount;
	}
	public static LicznikMin getInstance() {
	    if (Instance == null)
	    synchronized(LicznikMin.class) {
	         if (Instance == null) Instance = new LicznikMin();
	    	}
	     return Instance;
	}
	/** funkcja ustawiaj¹ca liczbê min */
	public void updateMineCount()
	{
		Gra g = Gra.getInstance();
		minecount.setText(String.format("%03d", g.getMinesToFind()));
	}
}
