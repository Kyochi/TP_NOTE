package schtroumpfs;

/**
 * Enumération de Zone
 * Permet de décrire la zone d'habitation d'un schtroumpf dans un village.
 * @author Baptiste CHARRIER
 * @version 1
 */
public enum Zone {
	/**
	 * Zone Sud.
	 */
 SUD("Zone Sud"),
 /**
  * Zone Nord.
  */
 NORD("Zone Nord");

 /**
  * Nom d'une zone.
  */
 private String nomzone;
 
 /**
  * Constructeur de Zone.
  * @param zone nouvelle Zone.
  */
 Zone(final String zone) {
	 nomzone = zone;
 }
/**
 * Getter de nomzone.
 * @return nomzone.
 */
public String getNomzone() {
	return nomzone;
}
}
