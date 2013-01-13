package io.saper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

class Czas implements Serializable
{
	private String czas;
	public Czas()
	{
		czas =  new String();
		czas="-";
	}
	public void setCzas(String nowy)
	{
		czas = nowy;
	}
	public String getCzas()
	{
		return czas;
	}
}

class Czasy implements Serializable
{
	Czas min;
	Czas med;
	Czas max;
	public Czasy()
	{
		min = new Czas();
		med = new Czas();
		max = new Czas();
	}
	public String getCzas(Plansza p)
	{
		if(p==Plansza.Min)return min.getCzas();
		if(p==Plansza.Med)return med.getCzas();
		return max.getCzas();
	}
	public void setCzas(Plansza p, String nowy)
	{
		if(p==Plansza.Min)min.setCzas(nowy);
		else if(p==Plansza.Med)med.setCzas(nowy);
		else max.setCzas(nowy);
	}
}

class Dane implements Serializable{
	private int ileRozegranych;
	private int ileWygranych;
	private Czasy czasy;
	public Dane()
	{
		ileRozegranych = 1;
		ileWygranych = 0;
		czasy = new Czasy();
	}
	public void zwiekszLiczbeGier()
	{
		ileRozegranych++;
	}
	public void zwiekszLiczbeWygranychGier()
	{
		ileWygranych++;
	}
	public String getCzas(Plansza p)
	{
		return czasy.getCzas(p);
	}
	public void setCzas(Plansza p, String nowy)
	{
		czasy.setCzas(p,nowy);
	}
}

public class DaneGraczy {
	private static volatile DaneGraczy Instance;
	private DaneGraczy(){nazwaGracza="";}
	private TextView stat;
	private Context context;
	private String nazwaGracza;
	private Plansza plansza;
	private HashMap<String,Dane> statystyki;
	public static DaneGraczy getInstance() {
	    if (Instance == null)
	    synchronized(DaneGraczy.class) {
	         if (Instance == null) Instance = new DaneGraczy();
	    	}
	     return Instance;
	}
	private void zapiszStatystyki()
	{
		try
		{
			FileOutputStream f = context.openFileOutput("saperStat", Context.MODE_PRIVATE);
			if(f == null)Wiadomosci.showMessage("null");
			ObjectOutputStream out = new ObjectOutputStream( f );
			//if(out == null)Wiadomosci.showMessage("null");
			out.writeObject( statystyki );
			out.close();
		}
		catch(FileNotFoundException e)
		{
			Wiadomosci.showMessage("plik nie znaleziony");
		}
		catch(NullPointerException e)
		{
			Wiadomosci.showMessage("b³¹d przy zapisie statystyk - null");
		}
		catch(SecurityException e)
		{
			Wiadomosci.showMessage("b³¹d przy zapisie statystyk - security");
		}
		catch(InvalidClassException e)
		{
			Wiadomosci.showMessage("i");
		}
		catch(NotSerializableException e)
		{
			Wiadomosci.showMessage("nieserializowalne");
		}
		catch(IOException e)
		{
			Wiadomosci.showMessage("b³¹d przy zapisie statystyk - IO");
		}
	}
	
	private void otworzStatystyki()
	{
		FileInputStream f = null;
		ObjectInputStream in = null;
		boolean czyOtwierac = true;
		try
		{
			f = context.openFileInput("saperStat");
			if(f == null)Wiadomosci.showMessage("null");
		}
		catch(FileNotFoundException e)
		{
			statystyki = new HashMap<String,Dane>();
			czyOtwierac = false;
		}
		catch(Exception e)
		{
			czyOtwierac = false;
			Wiadomosci.showMessage("wyjatek przy otwieraniu statystyk");
		}
        try {
        	if(czyOtwierac)
        	{
        		in = new ObjectInputStream( f );
        		HashMap<String,Dane> readObject = (HashMap<String,Dane>)in.readObject();
        		statystyki =  readObject;
        	}
        }
        catch(Exception e)
        {
        	Wiadomosci.showMessage("wyjatek przy wczytywaniu statystyk");
        }
        finally {
        	try{
        		if(czyOtwierac)in.close();
        	}catch(Exception e){}
        }
	}
	
	private String najlepszeNaPlanszy(Plansza p)
	{
		String Imie[]= {"-","-","-"};
		int Czasy[] = {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE};
		for (Map.Entry<String, Dane> entry : statystyki.entrySet())
		{ 
			String imie = entry.getKey();
			Dane d = entry.getValue();
			String czas = d.getCzas(p);
			if(!czas.equals("-"))
			{
				int czasInt = Integer.parseInt(czas);
				if(czasInt < Czasy[0])
				{
					Czasy[0] = czasInt;
					Imie[0] = imie;
				}
				else if(czasInt < Czasy[1])
				{
					Czasy[1] = czasInt;
					Imie[1] = imie;
				}
				else if(czasInt < Czasy[2])
				{
					Czasy[2] = czasInt;
					Imie[2] = imie;
				}
			}
		}
		String wynik="";
		for(int i=0;i<3;i++)
		{
			wynik += (i+1)+") "+Imie[i]+" : ";
			if(!Imie[i].equals("-")) wynik += Czasy[i]+" s\n";
			else wynik += "- s\n";
		}
		return wynik;
	}
	public void init(Context context, TextView stat)
	{
		this.context = context;
		this.stat = stat;
	}
	public void setNazwaGracza(String nazwa)
	{
		nazwaGracza=nazwa;
	}
	public String getNazwaGracza()
	{
		return nazwaGracza;
	}
	public void setPlansza(Plansza p)
	{
		plansza=p;
	}
	public Plansza getPlansza()
	{
		return plansza;
	}
	/**
	 * zmienia stan statystyk
	 * @param czas - czas gry
	 * @param czyWygrana - czy poprzednia gra zostala wygrana
	 */
	public void update(String czas, boolean czyWygrana)
	{
		//znajdz w mapie poprzednie dane gracza
		Dane d = statystyki.get(nazwaGracza);
		if(d == null)
		{
			d = new Dane();
			if(czyWygrana)
			{
				d.zwiekszLiczbeWygranychGier();
				d.setCzas(plansza,czas);
			}
		}
		else
		{
			d.zwiekszLiczbeGier();
			if(czyWygrana)
			{
				d.zwiekszLiczbeWygranychGier();
				String poprzedniCzas = d.getCzas(plansza);
				if(poprzedniCzas.equals("-") || 
						Integer.parseInt(poprzedniCzas) > Integer.parseInt(czas))
				{
					d.setCzas(plansza,czas);
					d.zwiekszLiczbeWygranychGier();
				}
			}
		}
		statystyki.put(nazwaGracza,d);
		zapiszStatystyki();
	}
	public String najlepszyWynik()
	{
		Dane d = statystyki.get(nazwaGracza);
		String wiadomosc="";
		if(d==null)wiadomosc="Brak twojego najlepszego wyniku";
		else
		{
			String czas = d.getCzas(plansza);
			if(czas.equals("-"))wiadomosc="Brak twojego najlepszego wyniku na tej planszy";
			else wiadomosc="Twój najlepszy wynik na tej planszy to "+czas+" s";
		}
		return wiadomosc;
	}

	public void wczytajStat()
	{
		otworzStatystyki();
	}
	
	public void wypiszStat()
	{
		String s = "Twoje najlepsze wyniki:\nPlansza 9x9: ";
		Dane d = statystyki.get(nazwaGracza);
		if(d == null)
		{
			s+="-\nPlansza 16x16: -\nPlansza 16x30: -\n\n";
		}
		else
		{
			String min = d.getCzas(Plansza.Min);
			String med = d.getCzas(Plansza.Med);
			String max = d.getCzas(Plansza.Max);
			s+=min+" s\nPlansza med: "+med+" s\nPlansza max: "+max+" s\n";
		}
		s+="Najlepsze wyniki na poszczegolnych planszach\n\n";
		s+="Na 9x9:\n"+najlepszeNaPlanszy(Plansza.Min);
		s+="Na 16x16:\n"+najlepszeNaPlanszy(Plansza.Med);
		s+="Na 16x30:\n"+najlepszeNaPlanszy(Plansza.Max);
		Wiadomosci.okienkoStat(s);
	}
	public void ustawListenera()
	{
		stat.setOnClickListener(new OnClickListener()
        {
			public void onClick(View v)
			{
				//tworzymy okienko dialogowe!!!
				wypiszStat();
			}
        	
        });
	}
}
