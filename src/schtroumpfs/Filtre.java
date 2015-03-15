package schtroumpfs;

/*
 * Interface Filtre � Schtroumpf.
 * @author Baptiste CHARRIER
 * @version 1
 */
public interface Filtre<Schtroumpf> {
	
	/*
	 * M�thode d'interface implicitement abstraite red�finie dans les classes de test acceptant un schtroumpf.
	 * @return true si le schtroumpf est accept�.
	 */
	public boolean accepte(Schtroumpf s) ;

}
