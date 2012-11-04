package io.saper;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
private TextView timer, minecount;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*tworzenie napisów(liczników)*/
        timer = (TextView) findViewById(R.id.timer);
        timer = (TextView) findViewById(R.id.licznik_min);
        /*zmienianie czcionki*/
        Typeface lcd_font = Typeface.createFromAsset(getAssets(), "fonts/LCDPHONE.ttf");
        timer.setTypeface(lcd_font);
        minecount.setTypeface(lcd_font);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
