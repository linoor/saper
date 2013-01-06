package io.saper;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class Buzka {
	private static volatile Buzka Instance;
	private ImageButton smiley;
	private Context context;
	private Buzka(){}
	public static Buzka getInstance() {
	    if (Instance == null)
	    synchronized(Buzka.class) {
	         if (Instance == null) Instance = new Buzka();
	    	}
	     return Instance;
	}
	public void init(Context context, ImageButton smiley)
	{
		this.context = context;
		this.smiley = smiley;
	}
	public void changeImage(int i)
	{
		smiley.setBackgroundResource(i);
	}
	public void ustawListenera()
	{
		smiley.setOnClickListener(new OnClickListener()
        {
			public void onClick(View v)
			{
				Gra g = Gra.getInstance();
				g.endGame();
				//tworzymy okienko dialogowe!!!
				Wiadomosci.okienko();
			}
        	
        });
	}
}
