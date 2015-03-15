package schtroumpfs;

/**
 * Classe d'exception de nom.
 * @author Baptiste CHARRIER
 * @version 1
 */
public class NomIncorrectException extends Exception{

	/**
	 * Constructeur de NomIncorrectException
	 * @param message Message décrivant l'exception levée.
	 */
	public NomIncorrectException(String message) {
		super(message);
		
	}
}
