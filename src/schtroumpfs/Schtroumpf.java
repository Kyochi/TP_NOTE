package schtroumpfs;

public class Schtroumpf {
	private String nom;
	private int humeur;
	private String zone;
	private int stockSalsePareille;
	public static int HUMEUR_MAX = 10;
	public static int HUMEUR_MIN = 0;
	
	public Schtroumpf(String nomS) throws NomIncorrectException{
		if (nomS.startsWith("Schtroumpf ") || nomS.startsWith("Grand Schtroumpf")) {
			nom = nomS;
		}
		else throw new NomIncorrectException();
		
	}

	public String getNom() {
		return nom;
	}

	public String getHumeur() {
		return humeur;
	}

	public String getZone() {
		return zone;
	}

	public int getStockSalsePareille() {
		return stockSalsePareille;
	}

}
