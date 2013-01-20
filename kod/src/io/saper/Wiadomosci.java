package io.saper;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Wiadomosci {
	//okienka i toasty
	static Context context;
	static ImageButton jakgrac, oprojekcie;
	public static void init(Context context, ImageButton jakgrac, ImageButton oprojekcie)
	{
		Wiadomosci.context = context;
		Wiadomosci.jakgrac = jakgrac;
		Wiadomosci.oprojekcie = oprojekcie;
	}
	private static void zacznij(Dialog dialog, EditText nazwaU, int rzedy, int kolumny, int miny)
	{
		Gra g = Gra.getInstance();
		String nazwa = nazwaU.getText().toString();
		if(!nazwa.equals(""))
		{
			if( nazwa.length() <= 10 )
			{
				try{
					DaneGraczy d = DaneGraczy.getInstance();
					d.setNazwaGracza(nazwa);
					if(miny==10)d.setPlansza(Plansza.Min);
					else if(miny==40)d.setPlansza(Plansza.Med);
					else d.setPlansza(Plansza.Max);
					g.ustaw(rzedy,kolumny,miny);
					g.startNewGame();
				}
				catch(Exception e){}
				finally{
					dialog.dismiss();
				}
			}
			else Wiadomosci.showMessage("Za d�uga nazwa u�ytkownika (ponad 10 znak�w)!!!");
		}
		else Wiadomosci.showMessage("Wpisz nazw� u�ytkownika!!!");
	}
	public static void ustawListenery()
	{
		jakgrac.setOnClickListener(new OnClickListener()
        {
			public void onClick(View v)
			{
				//tworzymy okienko dialogowe!!!
				Wiadomosci.okienkoGra();
			}
        	
        });
		oprojekcie.setOnClickListener(new OnClickListener()
        {
			public void onClick(View v)
			{
				String wiadomosc = "Projekt powstal w ramach kursu in�ynieria oprogramowania."
						+"\nlinoor + cosmia + moren126";
				Wiadomosci.showMessage(wiadomosc);
			}
        	
        });
	}
	/** funkcja pokazuj�ca okienko w przypadku wygranej
	 * @version 1.0
	 */
	public static void showDialogBox(String message, int seconds, boolean win)
	{
		CharSequence text = message + "\nCzas gry: " + String.valueOf(seconds) + " s";
		int duration = Toast.LENGTH_SHORT;
		
		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(Gravity.CENTER, 0, 0);
		
		LinearLayout l = (LinearLayout) toast.getView();
		ImageView i = new ImageView(context);
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
	/**funkcja wyswietlajace wiadomosc o blednych danych
	 * @param message - wyswietlana wiadomosc
	 */
	public static void showMessage(String message)
	{
		CharSequence text = message;
		int duration = Toast.LENGTH_SHORT;
		
		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}
	public static void okienko()
	{
		final Dialog dialog = new Dialog(context);
		dialog.setTitle("Wyb�r planszy");
		dialog.setContentView(R.layout.custom);

		// set the custom dialog components - text, image and button
		TextView text = (TextView) dialog.findViewById(R.id.text);
		text.setText("Wybierz jedn� z mo�liwych opcji oraz podaj nazw� u�ytkownika");
		ImageView image = (ImageView) dialog.findViewById(R.id.image);
		image.setImageResource(R.drawable.ic_launcher);

		final EditText nazwaU = (EditText) dialog.findViewById(R.id.username);
		DaneGraczy d = DaneGraczy.getInstance();
		nazwaU.setText(d.getNazwaGracza());
		Button dialogButton1 = (Button) dialog.findViewById(R.id.planszaMin);
		Button dialogButton2 = (Button) dialog.findViewById(R.id.planszaMed);
		Button dialogButton3 = (Button) dialog.findViewById(R.id.planszaMax);
		// if button is clicked, close the custom dialog
		dialogButton1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				zacznij(dialog,nazwaU,9,9,10);
				//Wiadomosci.showMessage(context, "ok");
			}
		});
		dialogButton2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				zacznij(dialog,nazwaU,16,16,40);
			}
		});
		dialogButton3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				zacznij(dialog,nazwaU,16,30,99);
			}
		});
		dialog.show();
	}
	public static void okienkoStat(String wiadomosc)
	{
		final Dialog dialog = new Dialog(context);
		dialog.setTitle("Statystyki");
		dialog.setContentView(R.layout.statystyki);

		Button dialogButton1 = (Button) dialog.findViewById(R.id.ok);
		dialogButton1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		TextView text = (TextView) dialog.findViewById(R.id.text);
		text.setText(wiadomosc);
		dialog.show();
	}
	public static void okienkoGra()
	{
		final Dialog dialog = new Dialog(context);
		dialog.setTitle("Jak gra�");
		dialog.setContentView(R.layout.statystyki);
		String wiadomosc="\nGra polega na tym, by odkryc wszystkie niezaminowane pola.\n"
				+"Mo�na sobie pomaga�, stawiaj�c na polu symbol flagi - nale�y u�y� d�ugiego klikni�cia."
				+"\nKolejne d�ugie klikni�cie postawi na tym polu pytajnik, a nast�pne je wyczy�ci."
				+"\nKr�tkie klikni�cia ods�aniaj� pole.\n"
				+"\n Now� gr� mo�na rozpocz�� klikaj�c bu�k�.";
		Button dialogButton1 = (Button) dialog.findViewById(R.id.ok);
		dialogButton1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		TextView text = (TextView) dialog.findViewById(R.id.text);
		text.setText(wiadomosc);
		dialog.show();
	}
}
