package io.saper;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
private TextView timer, minecount;
private ImageButton smiley;
private Block blocks[][];
private TableLayout pole_minowe;
private int numer_of_rows = 9;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*tworzenie napis�w(licznik�w)*/
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
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    private void showMineField()
    {
    	
    }
}
