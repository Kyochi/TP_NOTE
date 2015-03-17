package schtroumpfs;

/**
 * Interface Filtre � Schtroumpf.
 * @author Baptiste CHARRIER
 * @version 1
 * @param <Schtroumpf> filtre � schtroumpf.
 */
public interface Filtre<Schtroumpf> {
	
	/**
	 * M�thode d'interface implicitement abstraite 
	 * red�finie dans les classes de test acceptant un schtroumpf.
	 * @param s Schtroumpf soumis � acceptation.
	 * @return true si le schtroumpf est accept�.
	 */
	 boolean accepte(Schtroumpf s);

}
