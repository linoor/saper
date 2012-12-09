package io.saper;

import java.awt.font.NumericShaper;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
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
private int minesToFind = 10; // pozosta³e do znalezienia miny
/** maksymalna liczba min */
private int minesTotal = 10;

private Handler timer = new Handler();
private int czas = 0;

private boolean isTimerstarted = false; // jeœli true, to znaczy, ¿e zegar rozpocz¹³ odliczanie
private boolean areMinesSet = false; // jeœli true, to znaczy, ¿e miny zosta³y ustawione
private boolean isGameOver; // jeœli true, to gra zosta³a zakoñczona
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*tworzenie napisów(liczników)*/
        zegar = (TextView) findViewById(R.id.timer);
        minecount = (TextView) findViewById(R.id.licznik_min);
        minecount.setText(String.format("%03d", minesTotal));
        
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
    /**tworzy tablelayout i pokazuje pole minowe*/
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
    /**tworzy pole minowe*/
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
			
			// ustawiamy na final, ¿eby przekazaæ do klas anonimowych listenerów
			final int currentRow = wiersz;
			final int currentColumn = kolumna;
			
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
					
					if(!areMinesSet)
					{
						setMines();
						areMinesSet = true;
					}
					
					// jeœli pole nie jest zaznaczone flag¹
					if(!blocks[currentRow][currentColumn].isFlagged())
					{
						if(blocks[currentRow][currentColumn].isMined())
						{
							blocks[currentRow][currentColumn].setMineIcon();
						}
						
						if(blocks[currentRow][currentColumn].isCovered())
						{
							blocks[currentRow][currentColumn].uncover();
						}
					}
				}
				
			});
			
			
			// tutaj mo¿na zaimplementowaæ co siê dzieje po d³ugim klikniêciu
			blocks[wiersz][kolumna].setOnLongClickListener(new OnLongClickListener()
				{
					
					public boolean onLongClick(View v)
					{
						if(!blocks[currentRow][currentColumn].isFlagged() && minesToFind >= 1)
						{
							// zmiejszamy liczbê min do znalezienia
							minesToFind--;
							// ustawiamy ikonkê flagi
							blocks[currentRow][currentColumn].setFlagIcon(true);
							// ustawiamy znacznik
							blocks[currentRow][currentColumn].setFlagged(true);
							// update minecount
							updateMineCount();
							
						}
						else if(blocks[currentRow][currentColumn].isFlagged())
						{
							// zwiêkszamy liczbê min do znalezienia
							minesToFind++;
							// zmieniamy ikonke
							blocks[currentRow][currentColumn].setFlagIcon(false);
							// ustawiamy znacznik
							blocks[currentRow][currentColumn].setFlagged(false);
							// update mineCount
							updateMineCount();
						}
						return true;
					}
				});
		}
	}
}
/** rozpoczyna odliczanie czasu */
public void startTimer()
{
	if(czas == 0)
	{
		timer.removeCallbacks(updateTime);
		timer.postDelayed(updateTime, 500);
	}
}
/** Zadanie stworzone na potrzeby zegara ( klasa anonimowa )*/
private Runnable updateTime = new Runnable()
{
   public void run()
    {
    		long czasRozpoczeciaZadania = System.currentTimeMillis();
    		czas++; // odliczamy jedna sekundê
    		zegar.setText(String.format("%03d", czas)); // update zegara
    		
    		// ustaw zadanie na czas rozpoczecia zadania, a nastêpnie opóŸnij go o sekundê
    		timer.postAtTime(this, czasRozpoczeciaZadania);
    		timer.postDelayed(updateTime, 1000);
    }
};
/** metoda ustawiaj¹ca miny na losowe miejsca */
public void setMines()
{
	Random rand = new Random();
	
	int randomRow, randomColumn;
	
	
	// losujemy miejsce dla min tyle razy ile ma byæ min
	for(int i = 0; i < minesTotal; i++)
	{
		
		randomRow = rand.nextInt(number_of_rows) + 1;
		randomColumn = rand.nextInt(number_of_columns) + 1;
		
		// sprawdzamy czy na miejscu juz jest jakas mina
		if(blocks[randomRow][randomColumn].isMined())
		{
			i--;
		}
		blocks[randomRow][randomColumn].setMine();
	}
	
	// obliczanie ilosci min dookola pól
	
	int nearbyMines;
	
	for(int row = 0; row < number_of_rows + 2; row++)
	{
		for(int column = 0; column < number_of_columns + 2; column++)
		{
			nearbyMines = 0;
			
			// nie szukamy dla pierwszego i ostatniego rzêdu i kolumny (s¹ to rzêdy i kolumny pomocnicze)
			if((row != 0) && (row != (number_of_rows + 1)) && (column != 0) && (column != (number_of_columns + 1)))
			{
				// szukamy dooko³a pola
				for(int poprzedni_r = -1; poprzedni_r < 2; poprzedni_r++)
				{
					for(int poprzedni_k = -1; poprzedni_k < 2; poprzedni_k++)
					{
						if(blocks[row + poprzedni_r][column + poprzedni_k].isMined())
						{
							nearbyMines++;
						}
					}
				}
				
				// ustaw znalezion¹ liczbê min do pamiêci pola
				blocks[row][column].setMinesSurrounding(nearbyMines);
			}
		}
	}
}

/** funkcja ustawiaj¹ca liczbê min */
public void updateMineCount()
{
	minecount.setText(String.format("%03d", minesToFind));
}


/** funkcja sprawdzaj¹ca czy gracz wygra³ grê
 * @version 1.0
 * @return true jeœli wszystkie pola, które nie by³y zaminowane zosta³y odkryte,
 * false jeœli nie wszystkie zosta³y odkryte
 */
public boolean checkWin()
{
	for(int i = 0; i < number_of_rows + 1; i++)
	{
		for(int j = 0; j < number_of_columns + 1; j++)
		{
			if(blocks[i][j].isMined() && !blocks[i][j].isCovered())
			{
				return false;
			}
		}
	}
	return true;
}

}
