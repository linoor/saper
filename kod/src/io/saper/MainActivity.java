package io.saper;

import java.awt.font.NumericShaper;

import android.os.Bundle;
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
private TextView timer, minecount;
private ImageButton smiley;
private Block blocks[][];
private TableLayout pole_minowe;
private final int number_of_rows = 5;
private final int number_of_columns = 5;
private final int szerokosc_pola = 30;
private final int odstep = 3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*tworzenie napisów(liczników)*/
        timer = (TextView) findViewById(R.id.timer);
        minecount = (TextView) findViewById(R.id.licznik_min);
        
        /*tworzenie referencji do przycisku*/
        smiley = (ImageButton) findViewById(R.id.imageButton1);
        
        /*zmienianie czcionki licznikow*/
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/LCDM2B__.TTF");
        timer.setTypeface(font);
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
    		table.setLayoutParams(new TableRow.LayoutParams((szerokosc_pola + 2 * odstep) * number_of_columns, szerokosc_pola + 2 * odstep));
    		
    		for(int kolumna = 1; kolumna < number_of_columns + 1; kolumna++)
    		{
    			blocks[wiersz][kolumna].setLayoutParams(new TableRow.LayoutParams(  
    					szerokosc_pola + 2 * odstep,  
    					szerokosc_pola + 2 * odstep));
    			blocks[wiersz][kolumna].setPadding(odstep, odstep, odstep, odstep);
    			table.addView(blocks[wiersz][kolumna]);
    		}
    		pole_minowe.addView(table,new TableLayout.LayoutParams(  
					(szerokosc_pola + 2 * odstep) * number_of_columns, szerokosc_pola + 2 * odstep));;
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
    		}
    	}
    }
}
