package schtroumpfs;

public class SchtroumpfEquanime extends Schtroumpf {

	
	
	
	
	public SchtroumpfEquanime(String nomS, int humeurDef) throws NomIncorrectException {
		super(nomS);
		humeur = humeurDef;
		emoticone = (this.deBonneHumeur()) ?  ":D" : ":[";
	}
	
	
	

	
	


	
}
