package schtroumpfs;

public class SchtroumpfEquanime extends Schtroumpf {

	private String nom;
	
	public static int HUMEUR_MIN = 10;
	public static int HUMEUR_MAX = 0;
	
	public SchtroumpfEquanime(String nomS, int humeurDef) throws NomIncorrectException {
		super(nomS);
		humeur = humeurDef;
		emoticone = (this.deBonneHumeur()) ?  ":D" : ":[";
	}
	

	
	


	
}
