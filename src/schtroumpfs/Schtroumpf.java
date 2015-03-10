package schtroumpfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Schtroumpf {
	private String nom;
	protected int humeur;
	protected String emoticone;
	private Zone zone = Zone.SUD;
	private boolean malade = false;
	private int stockDeSalsePareille;
	/*
	public static int HUMEUR_MAX = 10;
	public static int HUMEUR_MIN = 0;
	public static int RECOLTE_MAX = 100;
	public static int FATIGUE = 1;
	public static int REVIGORATION = 1;
	public static int APPETIT_MAX = 20;
	*/
	public static int HUMEUR_MAX = 30;
	public static int HUMEUR_MIN = 10;
	public static int RECOLTE_MAX = 20;
	public static int FATIGUE = 3;
	public static int REVIGORATION = 8;
	public static int APPETIT_MAX = 3;
	
	public Schtroumpf(String nomS) throws NomIncorrectException{
		if ( (nomS.startsWith("Schtroumpf ")) 
			|| (nomS.equals("Grand Schtroumpf"))
			|| (nomS.equals("Schtroumpfette")) ) 		{
			nom = nomS;
			Random rand = new Random(); 
			int humeurRand = rand.nextInt((Schtroumpf.HUMEUR_MAX - Schtroumpf.HUMEUR_MIN) + 1) + Schtroumpf.HUMEUR_MIN; 
			humeur = humeurRand;
			emoticone = (this.deBonneHumeur()) ?  ":)" : ":(";
		}
		else throw new NomIncorrectException("Le nom saisi est incorrect");
		
	}
	
	public boolean estMalade() {
		return malade;
	}
	public void setMalade(boolean status) {
		malade = status;
	}
	public boolean deBonneHumeur() {
		return (humeur >= (Schtroumpf.HUMEUR_MAX + Schtroumpf.HUMEUR_MIN)/2 ? true : false);
	}
	public String toString() {
		return nom + "[" + humeur + "]" + emoticone;
	}
	public int recolteDeLaSalsepareille() {
		int recolteCourante = RECOLTE_MAX * humeur / HUMEUR_MAX;
		
		stockDeSalsePareille += recolteCourante;
		
		if(emoticone.equals(":D") || emoticone.equals(":[")) {
			if (nom.equals("Grand Schtroumpf") || nom.equals("Schtroumpfette") || nom.equals("Schtroumpf farceur"))  humeur = Math.max((Schtroumpf.HUMEUR_MAX+Schtroumpf.HUMEUR_MIN)/2 ,humeur-Schtroumpf.FATIGUE);
			else humeur = Math.max(Schtroumpf.HUMEUR_MIN, humeur-Schtroumpf.FATIGUE);
		}
		else {
			if (humeur >= Schtroumpf.HUMEUR_MIN + Schtroumpf.FATIGUE) humeur -= Schtroumpf.FATIGUE;
			else humeur = Schtroumpf.HUMEUR_MIN;
		}
		return recolteCourante;
	}
	public int mangeDeLaSalsepareille() {
		int salsemange = 0;
		Random rand = new Random(); 
		salsemange = rand.nextInt((Schtroumpf.APPETIT_MAX - 1) + 1) + 1;
		
		
		/*if (Schtroumpf.APPETIT_MAX > stockDeSalsePareille) {
			 
			salsemange = rand.nextInt((Schtroumpf.APPETIT_MAX - 1) + 1) + 1;
		}
		if(stockDeSalsePareille <= APPETIT_MAX && stockDeSalsePareille != 0) {
			
			salsemange = rand.nextInt((stockDeSalsePareille - 1) + 1) + 1;
		}
		else salsemange = 1; 
		
		if (stockDeSalsePareille >= salsemange) stockDeSalsePareille -= salsemange;
		if(stockDeSalsePareille < salsemange) stockDeSalsePareille = 0;
		*/
		stockDeSalsePareille -= salsemange;
		// douteux
		if(emoticone.equals(":D") || emoticone.equals(":[")) {
			if (nom.equals("Grand Schtroumpf") || nom.equals("Schtroumpfette") || nom.equals("Schtroumpf farceur"))  humeur = Math.min(Schtroumpf.HUMEUR_MAX, humeur +Schtroumpf.REVIGORATION);
			else humeur = Math.min((Schtroumpf.HUMEUR_MAX+Schtroumpf.HUMEUR_MIN)/2-1, humeur+Schtroumpf.REVIGORATION);
		}
		else {
			if (humeur >= Schtroumpf.HUMEUR_MAX - Schtroumpf.REVIGORATION || humeur == Schtroumpf.HUMEUR_MAX )  humeur = Schtroumpf.HUMEUR_MAX;
			else humeur += Schtroumpf.REVIGORATION;
		}
		
		return salsemange;
	}
	public static Schtroumpf attaqueDeLaMoucheBzz(ArrayList<Schtroumpf> schtroumpfCollection) {
		Random rand = new Random(); 
		int indexSchtroumpfPique = rand.nextInt((schtroumpfCollection.size() - 1) + 1) + 0; 
		(schtroumpfCollection.get(indexSchtroumpfPique)).setMalade(true);
		(schtroumpfCollection.get(indexSchtroumpfPique)).setHumeur(HUMEUR_MIN);
		return schtroumpfCollection.get(indexSchtroumpfPique);
	}
	public void mangeDuPollenDeTubereuse() {
		if(this.estMalade()) {
			humeur = HUMEUR_MAX;
			malade = false;
		}
	}
	public void mord(Schtroumpf s) throws MorsureException{
		if(this.estMalade() ) {
			if (!this.equals(s)) {
				s.setMalade(true);
				s.setHumeur(HUMEUR_MIN);
				System.out.println(s + " : Aie !");
			}
			else throw new MorsureException(this  + " ne peut pas se mordre lui-meme");
			
		}
		else {
			if (this.equals(s)) throw new MorsureException(this  + " ne peut pas se mordre lui-meme");
			else throw new MorsureException(this + " n'est pas malade");
		}
	}
	public void parle(String message) {
		ArrayList<String> motTransforme = new ArrayList<String>();
		String[] mots = {"appelle", "expliquer", "logiciel", "Liberte", "Egalite", "Fraternite", "ffffff", "besoin"};
		List<String> arrayToList = Arrays.asList(mots);
		motTransforme.addAll(arrayToList);
		
		if (this.estMalade()) {
			System.out.println(this + " : GNAP !");
		}
		else {

			for (String mot : motTransforme) {
				if(message.contains(mot)) {
					
				    message = message.replaceAll(mot, "schtroumpf");
				}
				if (message.contains("tire-bouchon")) {
					
					if (this.getZone() == Zone.NORD) message = message.replaceAll("tire-bouchon", "tire-schtroumpf");
					
					if (this.getZone() == Zone.SUD) message = message.replaceAll("tire-bouchon", "schtroumpf-bouchon");
				}
			}
			System.out.println(this + " : " + message);
			
			
		}
	}
	
	@Override
	public boolean equals (Object o) {
		return(o instanceof Schtroumpf) && ((Schtroumpf)o).nom.equals(nom);
	}
	@Override
	public int hashCode() {
		return Objects.hash(nom);
	}

	public int getStockDeSalsepareille() {
		return stockDeSalsePareille;
	}
	public void setStockDeSalsepareille(int nouveauStock) {
		stockDeSalsePareille = nouveauStock;
	}
	public String getNom() {
		return nom;
	}

	public int getHumeur() {
		return humeur;
	}
	public void setHumeur(int nouvelleHumeur) {
		humeur = nouvelleHumeur;
	}

	public Zone getZone() {
		return zone;
	}
	public void setZone(Zone newzone) {
		zone = newzone;
	}



}
