package schtroumpfs;

import java.util.ArrayList;
import java.util.Random;

public class Schtroumpf {
	private String nom;
	private int humeur;
	private String emoticone;
	private Zone zone = Zone.SUD;
	private boolean malade;
	private int stockDeSalsePareille =0;
	public static int HUMEUR_MAX = 10;
	public static int HUMEUR_MIN = 0;
	
	public Schtroumpf(String nomS) throws NomIncorrectException{
		if ( (nomS.startsWith("Schtroumpf ")) 
			|| (nomS.equals("Grand Schtroumpf"))
			|| (nomS.equals("Schtroumpfette")) ) 		{
			nom = nomS;
			Random rand = new Random(); 
			int humeurRand = rand.nextInt(10) + 0; 
			humeur = humeurRand;
			emoticone = (this.deBonneHumeur()) ?  ":)" : ":(";
		}
		else throw new NomIncorrectException();
		
	}
	
	public boolean estMalade() {
		return malade;
	}
	public boolean deBonneHumeur() {
		return (humeur >= 5 ? true : false);
	}
	public String toString() {
		return nom + "[" + humeur + "]" + emoticone;
	}
	

	public int getStockDeSalsepareille() {
		return stockDeSalsePareille;
	}
	public String getNom() {
		return nom;
	}

	public int getHumeur() {
		return humeur;
	}

	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone newzone) {
		zone = newzone;
	}

	public int getStockSalsePareille() {
		return stockDeSalsePareille;
	}

}
