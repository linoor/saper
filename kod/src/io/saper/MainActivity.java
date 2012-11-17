package io.saper;

import java.awt.font.NumericShaper;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
private TextView zegar, minecount;
private ImageButton smiley;
private Block blocks[][];
private TableLayout pole_minowe;
private int number_of_rows = 9;
private int number_of_columns = 9;
private int wielkosc_pola = 15;
private final int odstep = 3;
private int minesToFind; // pozosta³e do znalezienia miny

private Handler timer = new Handler();
private int czas = 0;

private boolean isTimerstarted; // jeœli true, to znaczy, ¿e zegar rozpocz¹³ odliczanie
private boolean areMinesSet; // jeœli true, to znaczy, ¿e miny zosta³y ustawione
private boolean isGameOver; // jeœli true, to gra zosta³a zakoñczona
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*tworzenie napisów(liczników)*/
        zegar = (TextView) findViewById(R.id.timer);
        minecount = (TextView) findViewById(R.id.licznik_min);
        
        /*tworzenie referencji do przycisku*/
        smiley = (ImageButton) findViewById(R.id.imageButton1);
        
        /*zmienianie czcionki licznikow*/
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/LCDM2B__.TTF");
        zegar.setTypeface(font);
        minecount.setTypeface(font);
        
        /*ustawianie pola minowego*/
        pole_minowe = (TableLayout) findViewById(R.id.pole_minowe);
        
        // tworzenie pola minowego
        createMineField();
        showMineField();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    /*tworzy tablelayout i pokazuje pole minowe*/
    private void showMineField()
    {
    	for(int wiersz = 1; wiersz < number_of_rows + 1; wiersz++)
    	{
    		TableRow table = new TableRow(this);
    		table.setLayoutParams(new TableRow.LayoutParams((wielkosc_pola + 2 * odstep) * number_of_columns, wielkosc_pola + 2 * odstep));
    		
    		for(int kolumna = 1; kolumna < number_of_columns + 1; kolumna++)
    		{
    			blocks[wiersz][kolumna].setLayoutParams(new TableRow.LayoutParams(  
    					wielkosc_pola + 2 * odstep,  
    					wielkosc_pola + 2 * odstep));
    			blocks[wiersz][kolumna].setPadding(odstep, odstep, odstep, odstep);
    			table.addView(blocks[wiersz][kolumna]);
    		}
    		pole_minowe.addView(table,new TableLayout.LayoutParams(  
					(wielkosc_pola + 2 * odstep) * number_of_columns, wielkosc_pola + 2 * odstep));;
    	}
    }
    /*tworzy pole minowe*/
    private void createMineField()
{
 // po 2 dodatkowe wiersze i kolumny (potrzebne do obliczania pobliskich min)
	blocks = new Block[number_of_rows + 2][number_of_columns + 2];
	
	//inicjalizowanie pól z minami
	for(int wiersz = 0; wiersz < number_of_rows + 2; wiersz++)
	{
		for(int kolumna = 0; kolumna < number_of_columns + 2; kolumna++)
		{
			blocks[wiersz][kolumna] = new Block(this);
			
			// tutaj mo¿na zaimplementowaæ co siê dzieje po krótkim klikniêciu
			blocks[wiersz][kolumna].setOnClickListener(new OnClickListener()
			{

				public void onClick(View v)
				{
					// jeœli zegar nie rozpocz¹³ odliczania, rozpocznij odliczanie
					if(!isTimerstarted)
					{
						startTimer();
						isTimerstarted = true;
					}
				}
				
			});
			
		}
	}
}
// rozpoczyna odliczanie czasu
public void startTimer()
{
	if(czas == 0)
	{
		timer.removeCallbacks(updateTime);
		timer.postDelayed(updateTime, 500);
	}
}
// Zadanie stworzone na potrzeby zegara ( klasa anonimowa )
private Runnable updateTime = new Runnable()
{
   public void run()
    {
    		long odliczanie = System.currentTimeMillis();
    		czas++; // odliczamy jedna sekundê
    		zegar.setText(String.format("%03d", czas)); // update zegara
    		
    		timer.postAtTime(this, odliczanie);
    		timer.postDelayed(updateTime, 1000);
    }
};
}
