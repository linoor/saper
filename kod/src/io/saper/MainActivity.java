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
private TextView zegar, minecount;
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
        
        /*tworzenie napisów(liczników)*/
        zegar = (TextView) findViewById(R.id.timer);
        minecount = (TextView) findViewById(R.id.licznik_min);
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
        l.updateMineCount();
        // ustawianie klikniêcia przy buŸce
        b.init(context, smiley);
        b.ustawListenera();
               
        // zapytanie o now¹ grê
        Wiadomosci.okienko();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}