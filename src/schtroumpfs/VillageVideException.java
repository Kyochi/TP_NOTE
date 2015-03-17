package schtroumpfs;

/**
 * Classe d'exception de village vide.
 * @author Baptiste CHARRIER
 * @version 1
 */
@SuppressWarnings("serial")
public class VillageVideException extends Exception {

	/**
	 * Constructeur de VillageVideExeption.
	 * @param message Message d�crivant l'exception lev�e.
	 */
	public VillageVideException(final String message) {
		super(message);
	}
}
