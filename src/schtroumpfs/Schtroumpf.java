package schtroumpfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Schtroumpf {
	private String nom;
	private int humeur;
	private String emoticone;
	private Zone zone = Zone.SUD;
	private boolean malade = false;
	private int stockDeSalsePareille =0;
	
	public static int HUMEUR_MAX = 10;
	public static int HUMEUR_MIN = 0;
	public static int RECOLTE_MAX = 100;
	public static int FATIGUE = 1;
	public static int REVIGORATION = 1;
	public static int APPETIT_MAX = 20;
	
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
		else throw new NomIncorrectException("Le nom saisi est incorrect");
		
	}
	
	public boolean estMalade() {
		return malade;
	}
	public void setMalade(boolean status) {
		malade = status;
	}
	public boolean deBonneHumeur() {
		return (humeur >= 5 ? true : false);
	}
	public String toString() {
		return nom + "[" + humeur + "]" + emoticone;
	}
	public int recolteDeLaSalsepareille() {
		int recolteCourante = RECOLTE_MAX * humeur / HUMEUR_MAX;
		stockDeSalsePareille += recolteCourante;
		if (humeur > HUMEUR_MIN) humeur -= FATIGUE;
		else humeur = HUMEUR_MIN;
		return recolteCourante;
	}
	public int mangeDeLaSalsepareille() {
		Random rand = new Random(); 
		int salsemange = rand.nextInt(APPETIT_MAX) + 1; 
		stockDeSalsePareille -= salsemange;
		if(humeur < HUMEUR_MAX) humeur += REVIGORATION;
		else humeur = HUMEUR_MAX;
		return salsemange;
	}
	public static Schtroumpf attaqueDeLaMoucheBzz(ArrayList<Schtroumpf> schtroumpfCollection) {
		Random rand = new Random(); 
		int indexSchtroumpfPique = rand.nextInt(schtroumpfCollection.size() - 1) + 0; 
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
	

	public int getStockDeSalsepareille() {
		return stockDeSalsePareille;
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

	public int getStockSalsePareille() {
		return stockDeSalsePareille;
	}

}
