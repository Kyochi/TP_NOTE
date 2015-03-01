package schtroumpfs;

public class SchtroumpfEquanime extends Schtroumpf {

	private String nom;
	
	public static int HUMEUR_MIN = 30;
	public static int HUMEUR_MAX = 10;
	
	public SchtroumpfEquanime(String nomS, int humeurDef) throws NomIncorrectException {
		super(nomS);
		humeur = humeurDef;
		if (humeurDef > Schtroumpf.HUMEUR_MAX) Schtroumpf.HUMEUR_MAX = humeurDef;
		emoticone = (this.deBonneHumeur()) ?  ":D" : ":[";
	}
	

	
	


	
}
