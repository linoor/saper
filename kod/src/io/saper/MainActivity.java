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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SlidingDrawer;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
private TextView zegar, minecount,statystyki;
private ImageButton smiley;
private TableLayout pole_minowe;
private ScrollView scroll;
final Context context = this;//uzywane przy okienkach

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Buzka b = Buzka.getInstance();
        Gra g =  Gra.getInstance();
        LicznikMin l = LicznikMin.getInstance();
        Zegar z = Zegar.getInstance();
        DaneGraczy d = DaneGraczy.getInstance();
        
        /*tworzenie napis�w(licznik�w)*/
        zegar = (TextView) findViewById(R.id.timer);
        minecount = (TextView) findViewById(R.id.licznik_min);
        statystyki = (TextView) findViewById(R.id.statystyki);
        /*ustawianie pola minowego*/
        pole_minowe = (TableLayout) findViewById(R.id.pole_minowe);
        //tworzenie referencji do przycisku
        smiley = (ImageButton) findViewById(R.id.imageButton1);
        /*zmienianie czcionki licznikow*/
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/LCDM2B__.TTF");
        zegar.setTypeface(font);
        minecount.setTypeface(font);  
        //inicjacje
        g.init(context, pole_minowe);
        g.setMinesTotal(10);
        z.init(zegar);
        l.init(minecount);
        Wiadomosci.init(context);
        d.init(context,statystyki);
        //wczytanie statystyk
        d.wczytajStat();
        //ustawienie klikniecia na napis ze statystykami
        d.ustawListenera();
        l.updateMineCount();
        // ustawianie klikni�cia przy bu�ce
        b.init(context, smiley);
        b.ustawListenera();
               
        // zapytanie o now� gr�
        Wiadomosci.okienko();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    public int rzedy()
    {
    	Gra g = Gra.getInstance();
    	return g.rzedy();
    }
    public int kolumny()
    {
    	Gra g = Gra.getInstance();
    	return g.kolumny();
    }
    public int getMinesToFind()
    {
    	Gra g = Gra.getInstance();
    	return g.getMinesToFind();
    }
    public int getMinesTotal()
    {
    	Gra g = Gra.getInstance();
    	return g.getMinesTotal();
    }
    public boolean checkWin()
    {
    	Gra g = Gra.getInstance();
    	return g.checkWin();
    }
    public boolean isTimerstarted()
    {
    	Zegar z = Zegar.getInstance();
    	return z.isTimerstarted();
    }
    public void stopTimer()
    {
    	Zegar z = Zegar.getInstance();
    	z.stopTimer();
    }
    public boolean isGameOver()
    {
    	Gra g = Gra.getInstance();
    	return g.isGameOver();
    }
    public boolean isAreMinesSet()
    {
    	Gra g = Gra.getInstance();
    	return g.isAreMinesSet();
    }
    public int getCzas()
    {
    	Zegar z = Zegar.getInstance();
    	return z.getCzas();
    }
    public Block[][] getBlocks()
    {
    	Gra g = Gra.getInstance();
    	return g.getBlocks();
    }
}