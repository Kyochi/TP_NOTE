package schtroumpfs;

/**
 * Enum�ration de Zone
 * Permet de d�crire la zone d'habitation d'un schtroumpf dans un village.
 * @author Baptiste CHARRIER
 * @version 1
 */
public enum Zone {
 SUD("Zone Sud"),
 NORD("Zone Nord");

 /**
  * Nom d'une zone
  */
 private final String nom_zone;
 
 /**
  * Constructeur de Zone.
  * @param zone nouvelle Zone
  */
 Zone(String zone) {
	 nom_zone = zone;
 }
}
