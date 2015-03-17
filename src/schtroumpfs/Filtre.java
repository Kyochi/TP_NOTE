package schtroumpfs;

/**
 * Interface Filtre à Schtroumpf.
 * @author Baptiste CHARRIER
 * @version 1
 * @param <Schtroumpf> filtre à schtroumpf.
 */
public interface Filtre<Schtroumpf> {
	
	/**
	 * Méthode d'interface implicitement abstraite 
	 * redéfinie dans les classes de test acceptant un schtroumpf.
	 * @param s Schtroumpf soumis à acceptation.
	 * @return true si le schtroumpf est accepté.
	 */
	 boolean accepte(Schtroumpf s);

}
