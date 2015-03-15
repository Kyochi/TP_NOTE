package schtroumpfs;

/**
 * Classe d'exception de morsure.
 * @author Baptiste CHARRIER
 * @version 1
 */
public class MorsureException extends Exception {
	/**
	 * Contructeur de MorsureException
	 * @param message Message décrivant l'exception levée.
	 */
	public MorsureException(String message) {
		super(message);
	}
}
