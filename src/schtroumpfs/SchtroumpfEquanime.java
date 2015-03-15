package schtroumpfs;

/**
 * Classe : SchtroumpfEquanime.
 * Schtroumpf ayant une humeur fixe.
 * @author Baptiste CHARRIER
 * @version 1
 */
public class SchtroumpfEquanime extends Schtroumpf {
	/**
	 * Humeur initiale d'un SchtroumpfEquanime
	 */
	private int humeurInitiale;
	/**
	 * Constructeur de SchtroumpfEquanime
	 * @param nomS nom du SchtroumpfEquanime
	 * @param humeurDef Humeur de création et définitive du Schtroumpf Equanime
	 * @throws NomIncorrectException Lève une exception si le nom du schtroumpf est incorrect.
	 */
	public SchtroumpfEquanime(String nomS, int humeurDef) throws NomIncorrectException {
		super(nomS);
		humeurInitiale = humeurDef;
		humeur = humeurInitiale;
		emoticone = (this.deBonneHumeur()) ?  ":D" : ":[";
	}
	/**
	 * Renvoie l'humeur initiale d'un SchtroumpfEquanime
	 * @return humeurInitiale
	 */
	public int getHumeurInitiale() {
		return humeurInitiale;
	}

}
