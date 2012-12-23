package io.saper;

import java.util.Random;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
private TextView zegar, minecount;
private ImageButton smiley;
private Block blocks[][];
private TableLayout pole_minowe;
private ScrollView scroll;
private int number_of_rows = 9;
private int number_of_columns = 9;
private int wielkosc_pola = 15;
private final int odstep = 3;
/** maksymalna liczba min */
private int minesTotal = 10;
private int minesToFind; // pozosta³e do znalezienia miny
final Context context = this;//uzywane przy okienkach

private Handler timer = new Handler();
private int czas = 0;

private boolean isTimerstarted = false; // jeœli true, to znaczy, ¿e zegar rozpocz¹³ odliczanie
private boolean areMinesSet = false; // jeœli true, to znaczy, ¿e miny zosta³y ustawione
private boolean isGameOver; // jeœli true, to gra zosta³a zakoñczonaSe

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*tworzenie napisów(liczników)*/
        zegar = (TextView) findViewById(R.id.timer);
        minecount = (TextView) findViewById(R.id.licznik_min);
        minecount.setText(String.format("%03d", getMinesTotal()));
        
        /*tworzenie referencji do przycisku*/
        smiley = (ImageButton) findViewById(R.id.imageButton1);
        // ustawianie klikniêcia przy buŸce
        smiley.setOnClickListener(new OnClickListener()
        {

			public void onClick(View v)
			{
				endGame();
				//tworzymy okienko dialogowe!!!
				okienko();

			}
        	
        });
        
        /*zmienianie czcionki licznikow*/
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/LCDM2B__.TTF");
        zegar.setTypeface(font);
        minecount.setTypeface(font);
        
        /*ustawianie pola minowego*/
        pole_minowe = (TableLayout) findViewById(R.id.pole_minowe);
        
        // rozpoczecie gry
        startNewGame();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    /** Funkcja zwracaj¹ca status gry
     * @return true jeœli gra zosta³a zakoñczona, false jeœli nie zosta³a zakoñczona
     */
    public boolean isGameOver()
    {
    	return isGameOver;
    }
    /** funkcja rozpoczynaj¹ca grê
     * 
     */
    public void startNewGame()
    {        
        setMinesToFind(getMinesTotal());
        //poprawne wyswietlanie liczby min
        minecount.setText(String.format("%03d", getMinesTotal()));
     // tworzenie pola minowego
        //showDialogBox("Rozmiar planszy:"+number_of_rows+" "+number_of_columns, czas, false);
        createMineField();
        showMineField();
        isGameOver = false;
        setCzas(0);
    }
    /**tworzy tablelayout i pokazuje pole minowe*/
    private void showMineField()
    {
    	for(int wiersz = 1; wiersz < number_of_rows + 1; wiersz++)
    	{
    		//dynamiczny rozmiar pola
    		int rozmiarPola = wielkosc_pola;
    		int odstepPola = odstep;
    		if(number_of_rows==16)
    		{
    			rozmiarPola=10;
    			odstepPola=2;
    		}
    		else if(number_of_rows==30)
    		{
    			rozmiarPola=6;
    			odstepPola=1;
    		}
    		TableRow table = new TableRow(this);
    		table.setLayoutParams(new TableRow.LayoutParams(
    				(rozmiarPola + 2 * odstepPola) * number_of_columns, 
    				rozmiarPola + 2 * odstepPola));
    		
    		for(int kolumna = 1; kolumna < number_of_columns + 1; kolumna++)
    		{
    			getBlocks()[wiersz][kolumna].setLayoutParams(new TableRow.LayoutParams(  
    					rozmiarPola + 2 * odstepPola,  
    					rozmiarPola + 2 * odstepPola));
    			getBlocks()[wiersz][kolumna].setPadding(
    					odstepPola, odstepPola, odstepPola, odstepPola);
    			table.addView(getBlocks()[wiersz][kolumna]);
    		}
    		pole_minowe.addView(table,new TableLayout.LayoutParams(  
					(rozmiarPola + 2 * odstepPola) * number_of_columns, 
					rozmiarPola + 2 * odstepPola));;
    	}
    }
    /**tworzy pole minowe*/
    private void createMineField()
{
 // po 2 dodatkowe wiersze i kolumny (potrzebne do obliczania pobliskich min)
	setBlocks(new Block[number_of_rows + 2][number_of_columns + 2]);
	//showDialogBox("Rozmiar planszy:"+number_of_rows+" "+number_of_columns, czas, false);
	//inicjalizowanie pól z minami
	for(int wiersz = 0; wiersz < number_of_rows + 2; wiersz++)
	{
		for(int kolumna = 0; kolumna < number_of_columns + 2; kolumna++)
		{
			getBlocks()[wiersz][kolumna] = new Block(this);
			
			// ustawiamy na final, ¿eby przekazaæ do klas anonimowych listenerów
			final int currentRow = wiersz;
			final int currentColumn = kolumna;
			
			// tutaj mo¿na zaimplementowaæ co siê dzieje po krótkim klikniêciu
			getBlocks()[wiersz][kolumna].setOnClickListener(new OnClickListener()
			{
				public void onClick(View v)
				{
					// jeœli zegar nie rozpocz¹³ odliczania, rozpocznij odliczanie
					if(!isTimerstarted())
					{
						startTimer();
						setTimerstarted(true);
					}
					
					if(!isAreMinesSet())
					{
						setMines(currentRow,currentColumn);
						setAreMinesSet(true);
					}
					
					// jeœli pole nie jest zaznaczone flag¹
					if(!getBlocks()[currentRow][currentColumn].isFlagged())
					{
						if(getBlocks()[currentRow][currentColumn].isMined())
						{
							getBlocks()[currentRow][currentColumn].setMineIcon();
							gameLose();
						}
						
						if(getBlocks()[currentRow][currentColumn].isCovered())
						{
							getBlocks()[currentRow][currentColumn].uncover();
						}
					}
					
					// sprawdzenie czy gra zosta³a zakoñczona
					if(checkWin())
					{
						gameWin();
					}
				}
			});
			
			
			// tutaj mo¿na zaimplementowaæ co siê dzieje po d³ugim klikniêciu
			getBlocks()[wiersz][kolumna].setOnLongClickListener(new OnLongClickListener()
				{
					
					public boolean onLongClick(View v)
					{
						if(!getBlocks()[currentRow][currentColumn].isFlagged() && getMinesToFind() >= 1)
						{
							// zmiejszamy liczbê min do znalezienia
							setMinesToFind(getMinesToFind() - 1);
							// ustawiamy ikonkê flagi
							getBlocks()[currentRow][currentColumn].setFlagIcon(true);
							// ustawiamy znacznik
							getBlocks()[currentRow][currentColumn].setFlagged(true);
							// update minecount
							updateMineCount();
							
						}
						else if(getBlocks()[currentRow][currentColumn].isFlagged())
						{
							// zwiêkszamy liczbê min do znalezienia
							setMinesToFind(getMinesToFind() + 1);
							// zmieniamy ikonke
							getBlocks()[currentRow][currentColumn].setFlagIcon(false);
							// ustawiamy znacznik
							getBlocks()[currentRow][currentColumn].setFlagged(false);
							// update mineCount
							updateMineCount();
						}
						return true;
					}
				});
		}
	}
}
/**
 * tworzy okienko, ktore pojawia sie po kliknieciu buzki
 */
public void okienko()
{
	final Dialog dialog = new Dialog(context);
	dialog.setTitle("Wybor planszy");
	dialog.setContentView(R.layout.custom);
	

	// set the custom dialog components - text, image and button
	TextView text = (TextView) dialog.findViewById(R.id.text);
	text.setText("Wybierz jedna z mozliwych opcji");
	ImageView image = (ImageView) dialog.findViewById(R.id.image);
	image.setImageResource(R.drawable.ic_launcher);

	Button dialogButton1 = (Button) dialog.findViewById(R.id.planszaMin);
	Button dialogButton2 = (Button) dialog.findViewById(R.id.planszaMed);
	Button dialogButton3 = (Button) dialog.findViewById(R.id.planszaMax);
	// if button is clicked, close the custom dialog
	dialogButton1.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
			ustaw(9,9,10);
			startNewGame();
			dialog.dismiss();
		}
	});
	dialogButton2.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
			ustaw(16,16,40);
			startNewGame();
			dialog.dismiss();
		}
	});
	dialogButton3.setOnClickListener(new OnClickListener() {
		public void onClick(View v) {
			ustaw(16,30,99);
			startNewGame();
			dialog.dismiss();
		}
	});
	dialog.show();
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
/** metoda ustawiaj¹ca miny na losowe miejsca */
public void setMines(int blockRow, int BlockColumn)
{
	Random rand = new Random();
	
	int randomRow, randomColumn;
	
	
	// losujemy miejsce dla min tyle razy ile ma byæ min
	for(int i = 0; i < getMinesTotal(); i++)
	{
		
		randomRow = rand.nextInt(number_of_rows) + 1;
		randomColumn = rand.nextInt(number_of_columns) + 1;
		
		// sprawdzamy czy to blok klikniety jako pierwszy ( jesli tak, to nie stawiamy tam miny)
		if(randomRow == blockRow && randomColumn == BlockColumn)
		{
			i--;
			continue;
		}
		// sprawdzamy czy na miejscu juz jest jakas mina
		//angela: po wyborze innego rozmiatu tutaj rzuca wyjatkiem!
		//przekroczenie zakresu tablicy
		if(getBlocks()[randomRow][randomColumn].isMined())
		{
			i--;
			continue;
		}
		getBlocks()[randomRow][randomColumn].setMine();
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
						if(getBlocks()[row + poprzedni_r][column + poprzedni_k].isMined())
						{
							nearbyMines++;
						}
					}
				}
				
				// ustaw znalezion¹ liczbê min do pamiêci pola
				getBlocks()[row][column].setMinesSurrounding(nearbyMines);
			}
		}
	}
}

/** funkcja ustawiaj¹ca liczbê min */
public void updateMineCount()
{
	minecount.setText(String.format("%03d", getMinesToFind()));
}


/** funkcja sprawdzaj¹ca czy gracz wygra³ grê
 * @version 1.0
 * @return true jeœli wszystkie pola, które nie by³y zaminowane zosta³y odkryte,
 * false jeœli nie wszystkie zosta³y odkryte
 */
public boolean checkWin()
{
	for(int i = 1; i < number_of_rows + 1; i++)
	{
		for(int j = 1; j < number_of_columns + 1; j++)
		{
			if(getBlocks()[i][j].isFlagged())
			{
				continue;
			}
			else if(!getBlocks()[i][j].isMined() && getBlocks()[i][j].isCovered())
			{
				return false;
			}
		}
	}
	return true;
}
/** funkcja wywo³ywana przy wygraniu gry
 * 
 */
public void gameWin()
{
	stopTimer();
	isGameOver = true;
	
	smiley.setBackgroundResource(R.drawable.smiech);
	
	activateButtons(false);
	//niech wyzeruje licznik min!
	minecount.setText("000");
	
	showDialogBox("Gratulacje, wygra³eœ!",getCzas(),true);
	
}
/** funkcja wywo³ywana przy przegraniu gry
 * 
 */
public void gameLose()
{
	stopTimer();
	isGameOver = true;
	
	smiley.setBackgroundResource(R.drawable.placz);
	
	activateButtons(false);
	//dodalam, bo tutaj chcemy, by wyzerowalo licznik 
	minecount.setText("000");
	
	showDialogBox("Niestety, przegra³eœ!", getCzas(), false);
	
}
/** funkcja w³¹czaj¹ca i wy³¹czaj¹ca przyciski
 * 
 */
public void activateButtons(boolean b)
{
	if(!b)
	{
		// wy³¹czanie przycisków
		for(int i = 0; i < number_of_rows + 1; i++)
		{
			for(int j = 0; j < number_of_columns + 1; j++)
			{
				getBlocks()[i][j].setClickable(false);
				getBlocks()[i][j].setLongClickable(false);
			}
		}
	}
	else
	{
		// w³¹czenie przycisków
		for(int i = 0; i < number_of_rows + 1; i++)
		{
			for(int j = 0; j < number_of_columns + 1; j++)
			{
				getBlocks()[i][j].setClickable(true);
				getBlocks()[i][j].setLongClickable(true);
			}
		}
	}
}
/** funkcja koñcz¹ca obecn¹ grê
 * 
 */
public void endGame()
{
	isGameOver = true;
	stopTimer();
	zegar.setText("000");
	setTimerstarted(false);
	setAreMinesSet(false);
	isGameOver = false;
	setMinesToFind(0);
	
	pole_minowe.removeAllViews();
}

/** funkcja pokazuj¹ca okienko w przypadku wygranej
 * @version 0.0
 */
public void showDialogBox(String message, int seconds, boolean win)
{
	Context context = getApplicationContext();
	CharSequence text = message + "\nCzas gry: " + String.valueOf(seconds) + " sekund";
	int duration = Toast.LENGTH_LONG;
	
	Toast toast = Toast.makeText(context, text, duration);
	toast.setGravity(Gravity.CENTER, 0, 0);
	
	LinearLayout l = (LinearLayout) toast.getView();
	ImageView i = new ImageView(getApplicationContext());
	if(win)
	{
		i.setBackgroundResource(R.drawable.smiech);
	}
	else
	{
		i.setBackgroundResource(R.drawable.placz);
	}
	l.addView(i,0);
	
	
	toast.show();
}
/**funkcja ustalajaca rozmiar planszy i liczbe min
 * @param rows liczba rzedow planszy
 * @param columns liczba kolumn planszy
 * @param mines liczba min na planszy
 */
public void ustaw(int columns, int rows, int mines)
{
	this.number_of_rows = rows;
	this.number_of_columns = columns;
	this.setMinesTotal(mines);
}

public int rzedy()
{
	return number_of_rows;
}

public int kolumny()
{
	return number_of_columns;
}
public int getCzas() {
	return czas;
}
public void setCzas(int czas) {
	this.czas = czas;
}
public int getMinesToFind() {
	return minesToFind;
}
public void setMinesToFind(int minesToFind) {
	this.minesToFind = minesToFind;
}
public int getMinesTotal() {
	return minesTotal;
}
public void setMinesTotal(int minesTotal) {
	this.minesTotal = minesTotal;
}
public boolean isTimerstarted() {
	return isTimerstarted;
}
public void setTimerstarted(boolean isTimerstarted) {
	this.isTimerstarted = isTimerstarted;
}
public boolean isAreMinesSet() {
	return areMinesSet;
}
public void setAreMinesSet(boolean areMinesSet) {
	this.areMinesSet = areMinesSet;
}
public Block[][] getBlocks() {
	return blocks;
}
public void setBlocks(Block blocks[][]) {
	this.blocks = blocks;
}

}