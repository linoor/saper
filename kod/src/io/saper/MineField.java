package io.saper;

import java.util.Random;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MineField {
	private static volatile MineField Instance;
	private MineField(){}
	public static MineField getInstance() {
	    if (Instance == null)
	    synchronized(MineField.class) {
	         if (Instance == null) Instance = new MineField();
	    	}
	     return Instance;
	}
	private Block blocks[][];
	private int number_of_rows;
	private int number_of_columns;
	private int wielkosc_pola = 20;
	private final int odstep = 3;
	public static int currentRow;
	public static int currentColumn;
	/**funkcja ustalajaca rozmiar planszy
	 * @param rows liczba rzedow planszy
	 * @param columns liczba kolumn planszy
	 */
	public void ustaw(int columns, int rows)
	{
		this.number_of_rows = rows;
		this.number_of_columns = columns;
	}
	public int rzedy()
	{
		return number_of_rows;
	}
	public int kolumny()
	{
		return number_of_columns;
	}
	private void krotkieKlikniecie(int currentRow, int currentColumn)
	{
		//krotka zmiana miny buzki!
		MineField.currentColumn = currentColumn;
		MineField.currentRow = currentRow;
		Buzka b = Buzka.getInstance();
		Gra g = Gra.getInstance();
    	b.changeImage(R.drawable.zdziwienie);
    	if(!g.isAreMinesSet())
		{
					setMines(currentRow,currentColumn);
					g.setAreMinesSet(true);
		}
    	final Handler handler = new Handler();
    	handler.postDelayed(new Runnable() {
    	  @Override
    	  public void run() {
    		  Buzka b = Buzka.getInstance();
    		  Gra g = Gra.getInstance();
    		  Zegar z = Zegar.getInstance();
    		  MineField m = MineField.getInstance();
    		  int currentRow = MineField.currentRow;
    		  int currentColumn = MineField.currentColumn;
    		  b.changeImage(R.drawable.usmiech);
    		  if(!z.isTimerstarted())
    		  {
    					z.startTimer();
    					z.setTimerstarted(true);
    		  }
    		
    		  /*if(!g.isAreMinesSet())
    		  {
    					m.setMines(currentRow,currentColumn);
    					g.setAreMinesSet(true);
    		  }*/
    		
    		  // je�li pole nie jest zaznaczone flag�
    		  if(!getBlocks()[currentRow][currentColumn].isFlagged() &&
    					!getBlocks()[currentRow][currentColumn].isQuestionMark())
    		  {
    				rippleEffect(currentRow, currentColumn);
    			
    				if(getBlocks()[currentRow][currentColumn].isMined())
    				{
    						getBlocks()[currentRow][currentColumn].setMineIcon();
    						g.gameLose();
    				}
    			
    				else if(getBlocks()[currentRow][currentColumn].isCovered())
    				{
    						getBlocks()[currentRow][currentColumn].uncover();
    				}
    		  }
    		
    		  // sprawdzenie czy gra zosta�a zako�czona
    		  if(g.checkWin())
    		  {
    					g.gameWin();
    		  }
    	  }
    	}, 100);
	}
	private void dlugieKlikniecie(int currentRow, int currentColumn)
	{
		Gra g = Gra.getInstance();
		LicznikMin l = LicznikMin.getInstance();
		if(!getBlocks()[currentRow][currentColumn].isFlagged() &&
				g.getMinesToFind() >= 1 &&
			getBlocks()[currentRow][currentColumn].isCovered() &&
			!getBlocks()[currentRow][currentColumn].isQuestionMark())
		{
			// zmiejszamy liczb� min do znalezienia
			g.setMinesToFind(g.getMinesToFind() - 1);
			// ustawiamy ikonk� flagi
			getBlocks()[currentRow][currentColumn].setFlagIcon(true);
			// update minecount
			l.updateMineCount();
		
		}
		else if(getBlocks()[currentRow][currentColumn].isFlagged())
		{
			// zwi�kszamy liczb� min do znalezienia
			g.setMinesToFind(g.getMinesToFind() + 1);
			// zmieniamy ikonke na pytajnik
			getBlocks()[currentRow][currentColumn].setFlagIcon(false);
			// ustawiamy znacznik pytajnika
			getBlocks()[currentRow][currentColumn].setQuestionMarkIcon(true);
			// update mineCount
			l.updateMineCount();
		}
		else if(getBlocks()[currentRow][currentColumn].isQuestionMark())
		{
			// zmieniamy ikonke
			getBlocks()[currentRow][currentColumn].setQuestionMarkIcon(false);
		}
		if(g.getMinesToFind() == 0)
		{
			if(g.checkWin())
			{
				g.gameWin();
			}
		}
	}
	private void akcjePoKliknieciu(Context context, int wiersz, int kolumna)
	{
		getBlocks()[wiersz][kolumna] = new Block(context);
		// ustawiamy na final, �eby przekaza� do klas anonimowych listener�w
		final int currentRow = wiersz;
		final int currentColumn = kolumna;
		// tutaj mo�na zaimplementowa� co si� dzieje po kr�tkim klikni�ciu
		getBlocks()[wiersz][kolumna].setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				krotkieKlikniecie(currentRow,currentColumn);
			}
		});
		// tutaj mo�na zaimplementowa� co si� dzieje po d�ugim klikni�ciu
		getBlocks()[wiersz][kolumna].setOnLongClickListener(new OnLongClickListener()
		{
			public boolean onLongClick(View v)
			{
				dlugieKlikniecie(currentRow,currentColumn);
				return true;
			}
		});
	}
	/** metoda ustawiaj�ca miny na losowe miejsca */
	public void setMines(int blockRow, int BlockColumn)
	{
		Random rand = new Random();
		
		int randomRow, randomColumn;
		Gra g = Gra.getInstance();
		// losujemy miejsce dla min tyle razy ile ma by� min
		for(int i = 0; i < g.getMinesTotal(); i++)
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
		
		// obliczanie ilosci min dookola p�l
		
		int nearbyMines;
		
		for(int row = 0; row < number_of_rows + 2; row++)
		{
			for(int column = 0; column < number_of_columns + 2; column++)
			{
				nearbyMines = 0;
				
				// nie szukamy dla pierwszego i ostatniego rz�du i kolumny (s� to rz�dy i kolumny pomocnicze)
				if((row != 0) && (row != (number_of_rows + 1)) && (column != 0) && (column != (number_of_columns + 1)))
				{
					// szukamy dooko�a pola
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
					
					// ustaw znalezion� liczb� min do pami�ci pola
					getBlocks()[row][column].setMinesSurrounding(nearbyMines);
				}
				else
				{
					getBlocks()[row][column].setMinesSurrounding(0);
					getBlocks()[row][column].uncover();
				}
			}
		}
	}
	/** ripple effect */
	private void rippleEffect(int row, int column)
	{
		Block block = getBlocks()[row][column];
		Random rand = new Random(36);
		if(block.isFlagged() || block.isMined() || !block.isCovered())
		{
			return;
		}
		
		block.uncover();
		
		if(block.getMinesSurrounding() > 0)
		{
			return;
		}
		
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				final int wiersz = row + i - 1;
				final int kolumna = column + j - 1;
				if(blocks[wiersz][kolumna].isCovered() &&
						wiersz < number_of_rows + 1 &&
						kolumna < number_of_columns + 1 &&
						wiersz > 0 && kolumna > 0)
				{
					// t�umienie
					Handler handler = new Handler();
					handler.postDelayed(new Runnable()
					{

						public void run()
						{
							rippleEffect(wiersz, kolumna);
						}
						
					}, 10*rand.nextInt(40));
				}
			}
		}
	}
	/** funkcja w��czaj�ca i wy��czaj�ca przyciski*/
	public void activateButtons(boolean b)
	{
		if(!b)
		{
			// wy��czanie przycisk�w
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
			// w��czenie przycisk�w
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
	public Block[][] getBlocks() {
		return blocks;
	}
	private void setBlocks(Block blocks[][]) {
		this.blocks = blocks;
	}
    public void createMineField(Context context)
    {
    		// po 2 dodatkowe wiersze i kolumny (potrzebne do obliczania pobliskich min)
			setBlocks(new Block[number_of_rows + 2][number_of_columns + 2]);
			//inicjalizowanie p�l z minami
			for(int wiersz = 0; wiersz < number_of_rows + 2; wiersz++)
			{
				for(int kolumna = 0; kolumna < number_of_columns + 2; kolumna++)
				{
					akcjePoKliknieciu(context, wiersz, kolumna);
				}
			}
    }
    public void showMineField(Context context, TableLayout pole_minowe)
    {
    	for(int wiersz = 1; wiersz < number_of_rows + 1; wiersz++)
    	{
    		//dynamiczny rozmiar pola
    		int rozmiarPola = wielkosc_pola;
    		int odstepPola = odstep;
    		if(number_of_rows!=9)
    		{
    			rozmiarPola=13;
    			odstepPola=3;
    		}
    		TableRow table = new TableRow(context);
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
    /** funkcja odkrywajaca wszystkie miny*/
    public void odkryjMiny()
    {
    	for(int row = 1; row < number_of_rows + 1; row++)
    	{
    		for(int column = 1; column < number_of_columns; column++)
    		{
    			Block block = blocks[row][column];
    			if(block.isMined())
    			{
    				block.setMineIcon();
    			}
    		}
    	}
    }
}
