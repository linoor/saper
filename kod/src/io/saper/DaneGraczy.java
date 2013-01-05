package io.saper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

class Czas
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

class Czasy
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

class Dane{
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
	private String nazwaGracza;
	private Plansza plansza;
	private Map<String,Dane> statystyki;
	
	private void zapiszStatystki() throws Exception
	{
		ObjectOutputStream out = new ObjectOutputStream( new FileOutputStream( "saperStat" ) );
	    out.writeObject( statystyki );
	    out.close();
	}
	
	private void otworzStatystyki() throws Exception
	{
		FileInputStream f = null;
		try
		{
			f = new FileInputStream( "saperStat" );
		}
		catch(FileNotFoundException e)
		{
			statystyki = new HashMap<String,Dane>();
		}
		ObjectInputStream in = new ObjectInputStream( f );
        try {
            Map<String,Dane> readObject = (Map<String,Dane>)in.readObject();
			statystyki =  readObject;
        }
        finally {
            in.close();
        }
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
	public DaneGraczy(String nazwa, Plansza p) throws Exception
	{
		setNazwaGracza(nazwa);
		setPlansza(p);
		otworzStatystyki();
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
				if(poprzedniCzas.equals("-") || (poprzedniCzas.compareTo(czas) > 0))
				{
					d.setCzas(plansza,czas);
					d.zwiekszLiczbeWygranychGier();
				}
			}
		}
		statystyki.put(nazwaGracza,d);
	}

}
