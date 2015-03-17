package schtroumpfs;

/**
 * Classe : SchtroumpfEquanime.
 * Schtroumpf ayant une humeur fixe.
 * @author Baptiste CHARRIER
 * @version 1
 */
public class SchtroumpfEquanime extends Schtroumpf {
	/**
	 * Humeur initiale d'un SchtroumpfEquanime.
	 */
	private int humeurInitiale;
	/**
	 * Constructeur de SchtroumpfEquanime.
	 * @param nomS nom du SchtroumpfEquanime
	 * @param humeurDef Humeur de création et définitive du Schtroumpf Equanime
	 * @throws NomIncorrectException Lève une exception 
	 * si le nom du schtroumpf est incorrect.
	 */
	public SchtroumpfEquanime(final String nomS, 
			final int humeurDef) throws NomIncorrectException {
		super(nomS);
		humeurInitiale = humeurDef;
		this.setHumeur(humeurInitiale);
		if (this.deBonneHumeur()) {
			this.setEmoticone(":D");
		} else {
			this.setEmoticone(":[");
		}
	}
	/**
	 * Renvoie l'humeur initiale d'un SchtroumpfEquanime.
	 * @return humeurInitiale
	 */
	public final int getHumeurInitiale() {
		return humeurInitiale;
	}

}
