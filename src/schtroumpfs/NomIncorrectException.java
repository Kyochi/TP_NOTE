package schtroumpfs;

/**
 * Classe d'exception de nom.
 * @author Baptiste CHARRIER
 * @version 1
 */
@SuppressWarnings("serial")
public class NomIncorrectException extends Exception {

	/**
	 * Constructeur de NomIncorrectException.
	 * @param message Message d�crivant l'exception lev�e.
	 */
	public NomIncorrectException(final String message) {
		super(message);
		
	}
}
