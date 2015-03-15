package schtroumpfs;

/*
 * Interface Filtre à Schtroumpf.
 * @author Baptiste CHARRIER
 * @version 1
 */
public interface Filtre<Schtroumpf> {
	
	/*
	 * Méthode d'interface implicitement abstraite redéfinie dans les classes de test acceptant un schtroumpf.
	 * @return true si le schtroumpf est accepté.
	 */
	public boolean accepte(Schtroumpf s) ;

}
