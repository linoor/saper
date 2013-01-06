package io.saper;

import android.os.Handler;
import android.widget.TextView;

public class Zegar {
	private static volatile Zegar Instance;
	private TextView zegar;
	private int czas = 0;
	private boolean isTimerstarted = false; 
	private Handler timer = new Handler();
	private Zegar(){}
	public static Zegar getInstance() {
	    if (Instance == null)
	    synchronized(Zegar.class) {
	         if (Instance == null) Instance = new Zegar();
	    	}
	     return Instance;
	}
	public void init(TextView zegar)
	{
		this.zegar = zegar;
	}
	/** rozpoczyna odliczanie czasu */
	public void startTimer()
	{
		if(getCzas() == 0)
		{
			timer.removeCallbacks(updateTime);
			timer.postDelayed(updateTime, 500);
		}
	}
	/** funkcja zatrzymuj¹ca czas */
	public void stopTimer()
	{
		timer.removeCallbacks(updateTime);
		setTimerstarted(false);
	}
	/** Zadanie stworzone na potrzeby zegara ( klasa anonimowa )*/
	private Runnable updateTime = new Runnable()
	{
	   public void run()
	    {
	    		long czasRozpoczeciaZadania = System.currentTimeMillis();
	    		setCzas(getCzas() + 1); // odliczamy jedna sekundê
	    		zegar.setText(String.format("%03d", getCzas())); // update zegara
	    		
	    		// ustaw zadanie na czas rozpoczecia zadania, a nastêpnie opóŸnij go o sekundê
	    		timer.postAtTime(this, czasRozpoczeciaZadania);
	    		timer.postDelayed(updateTime, 1000);
	    }
	};
	public int getCzas() {
		return czas;
	}
	public void setCzas(int czas) {
		this.czas = czas;
	}
	public boolean isTimerstarted() {
		return isTimerstarted;
	}
	public void setTimerstarted(boolean isTimerstarted) {
		this.isTimerstarted = isTimerstarted;
	}
	public void update()
	{
		zegar.setText(String.format("%03d", getCzas()));
	}
}
