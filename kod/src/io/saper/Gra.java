package io.saper;

import android.content.Context;
import android.widget.TableLayout;

public class Gra {
	private static volatile Gra Instance;
	private boolean isGameOver;
	private boolean areMinesSet = false;
	private int minesTotal;
	private int minesToFind;
	private MineField m;
	private Context context;
	private TableLayout pole_minowe;
	private Gra(){}
	public static Gra getInstance() {
	    if (Instance == null)
	    synchronized(Gra.class) {
	         if (Instance == null) Instance = new Gra();
	    	}
	     return Instance;
	}
	public void init(Context context, TableLayout pole_minowe)
	{
		this.context = context;
		this.pole_minowe = pole_minowe;
	}
	public void ustaw(int columns, int rows, int mines)
	{	
		//tworzy nowe pole minowe
		m = new MineField();
		m.ustaw(columns, rows);
		setMinesTotal(mines);
	}
	public boolean isGameOver()
    {
    	return isGameOver;
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
    /** funkcja rozpoczynaj¹ca grê
     * 
     */
    public void startNewGame()
    {
    	LicznikMin l = LicznikMin.getInstance();
    	Zegar z = Zegar.getInstance();
        setMinesToFind(getMinesTotal());
        //poprawne wyswietlanie liczby min
        l.updateMineCount();
        // pokazuje pole minowe
        m.createMineField(context);
        m.showMineField(context,pole_minowe);
        isGameOver = false;
        z.setCzas(0);
    }
    /** funkcja sprawdzaj¹ca czy gracz wygra³ grê
     * @version 1.0
     * @return true jeœli wszystkie pola, które nie by³y zaminowane zosta³y odkryte,
     * false jeœli nie wszystkie zosta³y odkryte
     */
    public boolean checkWin()
    {
    	for(int i = 1; i < m.rzedy() + 1; i++)
    	{
    		for(int j = 1; j < m.kolumny() + 1; j++)
    		{
    			if(m.getBlocks()[i][j].isFlagged())
    			{
    				continue;
    			}
    			else if(!m.getBlocks()[i][j].isMined() && m.getBlocks()[i][j].isCovered())
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
    	LicznikMin l = LicznikMin.getInstance();
    	Zegar z = Zegar.getInstance();
    	Buzka b = Buzka.getInstance();
    	z.stopTimer();
    	isGameOver = true;
    	b.changeImage(R.drawable.smiech);
    	m.activateButtons(false);
    	//niech wyzeruje licznik min!
    	setMinesToFind(0);
    	l.updateMineCount();
    	DaneGraczy d = DaneGraczy.getInstance();
    	Wiadomosci.showDialogBox("Gratulacje, "+d.getNazwaGracza()+", wygra³eœ!",z.getCzas(),true);
    	
    }
    /** funkcja wywo³ywana przy przegraniu gry
     * 
     */
    public void gameLose()
    {
    	LicznikMin l = LicznikMin.getInstance();
    	Zegar z = Zegar.getInstance();
    	Buzka b = Buzka.getInstance();
    	z.stopTimer();
    	isGameOver = true;
    	b.changeImage(R.drawable.placz);
    	m.activateButtons(false);
    	setMinesToFind(0);
    	l.updateMineCount();
    	m.odkryjMiny();
    	DaneGraczy d = DaneGraczy.getInstance();
    	Wiadomosci.showDialogBox("Niestety, "+d.getNazwaGracza()+", przegra³eœ!", z.getCzas(), false);
    	
    }
    /** funkcja koñcz¹ca obecn¹ grê
     * 
     */
    public void endGame()
    {
    	Zegar z = Zegar.getInstance();
    	isGameOver = true;
    	z.stopTimer();
    	z.update();
    	z.setTimerstarted(false);
    	setAreMinesSet(false);
    	isGameOver = false;
    	setMinesToFind(0);
    	
    	pole_minowe.removeAllViews();
    }
    public boolean isAreMinesSet() {
    	return areMinesSet;
    }
    public void setAreMinesSet(boolean areMinesSet) {
    	this.areMinesSet = areMinesSet;
    }
    public int rzedy()
    {
    	return m.rzedy();
    }
    public int kolumny()
    {
    	return m.kolumny();
    }
    public Block[][] getBlocks()
    {
    	return m.getBlocks();
    }
}
