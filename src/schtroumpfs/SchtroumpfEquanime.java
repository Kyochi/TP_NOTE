package schtroumpfs;

public class SchtroumpfEquanime extends Schtroumpf {

	
	
	int humeurInitiale;
	
	public SchtroumpfEquanime(String nomS, int humeurDef) throws NomIncorrectException {
		super(nomS);
		humeur = humeurInitiale = humeurDef;
		emoticone = (this.deBonneHumeur()) ?  ":D" : ":[";
	}
	public int getHumeurInitiale() {
		return humeurInitiale;
	}
	
	
	

	
	


	
}
