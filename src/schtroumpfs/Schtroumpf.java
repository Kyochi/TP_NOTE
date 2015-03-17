package schtroumpfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Classe : Schtroumpf.
 * @author Baptiste CHARRIER
 * @version 1
 */
public class Schtroumpf {
	
/**
* Nom d'un schtroumpf.
*/
private String nom;

/**
 * Humeur d'un Schtroumpf.
 */
private int humeur;

/**
 * Emoticone refl�tant l'humeur d'un Schtroumpf.
 */
private String emoticone;
/**
 * Setter d'�moticone.
 * @param newemoticone nouvel �moticone.
 */
public final void setEmoticone(final String newemoticone) {
	this.emoticone = newemoticone;
}
/**
 * getter d'�moticone.
 * @return emoticone
 */
public final String getEmoticone() {
	return emoticone;
}
/**
 * Zone de base d'un Schtroumpf.
 */
private Zone zone = Zone.SUD;

/**
 * Etat de sant� d'un Schtroumpf.
 */
private boolean malade = false;

/**
 * Stock de SalsePareille d'un Schtroumpf.
 */
private int stockDeSalsePareille;

/**
 * Humeur maximum d'un Schtroumpf.
 */
public static final int HUMEUR_MAX = 30;

/**
 * Humeur minimale d'un Schtroumpf.
 */
public static final int HUMEUR_MIN = 10;

/**
 * Recolte Maximale possible par un Schtroumpf.
 */
public static final int RECOLTE_MAX = 20;

/**
 * Fatigue d'un Schtroumpf lors d'une r�colte.
 */
public static final int FATIGUE = 3;

/**
 * Revigoration d'un schtroumpf quand il mange de la salse.
 */
public static final int REVIGORATION = 8;

/**
 * App�tit maximum d'un Schtroumpf.
 */
public static final int APPETIT_MAX = 3;

/**
 * Constructeur de Schtroumpf.
 * @param nomS Nom du Schtroumpf.
 * @throws NomIncorrectException L�ve exception si le nom est incorrect.
 */
	public Schtroumpf(final String nomS) throws NomIncorrectException {
		if ((nomS.startsWith("Schtroumpf ")) 
			|| (nomS.equals("Grand Schtroumpf"))
			|| (nomS.equals("Schtroumpfette"))) {
			nom = nomS;
			Random rand = new Random(); 
			int humeurRand = rand.nextInt(
					(Schtroumpf.HUMEUR_MAX 
					- Schtroumpf.HUMEUR_MIN) + 1) 
					+ Schtroumpf.HUMEUR_MIN; 
			humeur = humeurRand;
			if (this.deBonneHumeur()) {
				emoticone = ":)";
			} else {
				emoticone = ":(";
			}
		} else {
			throw new NomIncorrectException("Le nom saisi est incorrect"); 
			}
		
	}
	
	/**
	 * Retourne si le schtroumpf est malade.
	 * @return True si le schtroumpf est malade.
	 */
	public final boolean estMalade() {
		return malade;
	}
	
	/**
	 * Setter de malade.
	 * @param status Nouveau status de sant� True ou False.
	 */
	public final void setMalade(final boolean status) {
		malade = status;
	}
	
	/**
	 * Retourne si le schtroumpf est de bonne humeur ou non.
	 * @return True si l'humeur du schtroumpf est sup�rieur � l'humeur moyenne.
	 */
	public final boolean deBonneHumeur() {
		return (humeur >= (Schtroumpf.HUMEUR_MAX + Schtroumpf.HUMEUR_MIN) / 2);
		
	}
	
	/**
	 * Renvoie une string sous la forme 
	 * "Nom [Humeur] emotione" d'un schtroumpf.(non-Javadoc).
	 * @see java.lang.Object#toString()
	 * @return Description Schtroumpf.
	 */
	public final String toString() {
		return nom + "[" + humeur + "]" + emoticone;
	}
	
	/**
	 * Permet au schtroumpf de r�colter de la 
	 * SalsePareille et modifie son humeur en cons�quent.
	 * @return La quantit� de Salse r�colt�e.
	 */
	public final int recolteDeLaSalsepareille() {
		int recolteCourante = RECOLTE_MAX * humeur / HUMEUR_MAX;
		
		stockDeSalsePareille += recolteCourante;
		
		if (emoticone.equals(":D") 
			|| emoticone.equals(":[")) {
			if (nom.equals("Grand Schtroumpf") 
					|| nom.equals("Schtroumpfette") 
					|| nom.equals("Schtroumpf farceur")) {
				humeur = Math.max((
						Schtroumpf.HUMEUR_MAX + Schtroumpf.HUMEUR_MIN) / 2 ,
						humeur - Schtroumpf.FATIGUE);
			} else {
				humeur = Math.max(Schtroumpf.HUMEUR_MIN, 
						humeur - Schtroumpf.FATIGUE);
			}
		} else {
			if (humeur >= Schtroumpf.HUMEUR_MIN + Schtroumpf.FATIGUE) {
				humeur -= Schtroumpf.FATIGUE;
			} else {
				humeur = Schtroumpf.HUMEUR_MIN;
			}
		}
		return recolteCourante;
	}
	
	/**
	 * Permet � un schtroumpf de manger de la salse.
	 * @return  La quantit� de salse mang�e.
	 */
	public final int mangeDeLaSalsepareille() {
		int salsemange = 0;
		Random rand = new Random(); 
		salsemange = rand.nextInt((Schtroumpf.APPETIT_MAX - 1) + 1) + 1;
		stockDeSalsePareille -= salsemange;
		
		if (emoticone.equals(":D") || emoticone.equals(":[")) {
			if (nom.equals("Grand Schtroumpf") 
					|| nom.equals("Schtroumpfette") 
					|| nom.equals("Schtroumpf farceur")) {
				humeur = Math.min(Schtroumpf.HUMEUR_MAX,
						humeur + Schtroumpf.REVIGORATION);
			} else {
				humeur = Math.min((
						Schtroumpf.HUMEUR_MAX + Schtroumpf.HUMEUR_MIN) / 2 - 1,
						humeur + Schtroumpf.REVIGORATION);
			}
		} else {
			if (humeur >= Schtroumpf.HUMEUR_MAX - Schtroumpf.REVIGORATION 
					|| humeur == Schtroumpf.HUMEUR_MAX) {
				humeur = Schtroumpf.HUMEUR_MAX;
			} else {
				humeur += Schtroumpf.REVIGORATION;
			}
		}
		
		return salsemange;
	}
	
	/**
	 * M�thode statique piquant un Schtroumpf 
	 * al�atoirement dans l'ArrayList de Schtroumpf pass�e en param�tre.
	 * @param schtroumpfCollection Liste de Schtroumpf pouvant �tre attaqu�.
	 * @return Renvoie le Schtroumpf qui a �t� piqu�.
	 */
	public static Schtroumpf attaqueDeLaMoucheBzz(final ArrayList<Schtroumpf>
				schtroumpfCollection) {
		Random rand = new Random(); 
		int indexSchtroumpfPique = rand.nextInt((schtroumpfCollection.size() 
									- 1) + 1) + 0; 
		(schtroumpfCollection.get(indexSchtroumpfPique)).setMalade(true);
		(schtroumpfCollection.get(indexSchtroumpfPique)).setHumeur(HUMEUR_MIN);
		return schtroumpfCollection.get(indexSchtroumpfPique);
	}
	
	/**
	 * Permet � un Schtroumpf de gu�rir d'une piqure de la mouche Bzz.
	 */
	public final void mangeDuPollenDeTubereuse() {
		if (this.estMalade()) {
			humeur = HUMEUR_MAX;
			malade = false;
		}
	}
	
	/**
	 * Permet � un Schtroumpf de mordre 
	 * un Schtroumpf pass� en param�tre.
	 * @param s Schtroumpf victime de la morsure.
	 * @throws MorsureException L�ve une exeption 
	 * si le schtroumpf se mord lui m�me.
	 */
	public final void mord(final Schtroumpf s) throws MorsureException {
		if (this.estMalade()) {
			if (!this.equals(s)) {
				s.setMalade(true);
				s.setHumeur(HUMEUR_MIN);
				System.out.println(s + " : Aie !");
			} else {
				throw new MorsureException(this  
						+ " ne peut pas se mordre lui-meme");
			}
			
		} else {
			if (this.equals(s)) {
				throw new MorsureException(this  
						+ " ne peut pas se mordre lui-meme");
			} else {
				throw new MorsureException(this + " n'est pas malade");
			}
		}
	}
	
	/**
	 * Permet � un Schtroumpf de s'exprimer dans la langue des schtroumpfs.
	 * @param message Phrase � faire dire.
	 */
	public final void parle(final String message) {
		ArrayList<String> motTransforme = new ArrayList<String>();
		String[] mots = {"appelle", "expliquer", "logiciel", 
				"Liberte", "Egalite", "Fraternite", "ffffff", "besoin"};
		List<String> arrayToList = Arrays.asList(mots);
		motTransforme.addAll(arrayToList);
		
		if (this.estMalade()) {
			System.out.println(this + " : GNAP !");
		}
		else {
		String mess = message;
			for (String mot : motTransforme) {
				if (mess.contains(mot)) {
					
				    mess = mess.replaceAll(mot, "schtroumpf");
				}
				if (mess.contains("tire-bouchon")) {
					
					if (this.getZone() == Zone.NORD) {
						mess = mess.replaceAll("tire-bouchon",
								"tire-schtroumpf");
					}
					
					if (this.getZone() == Zone.SUD) {
						mess = mess.replaceAll("tire-bouchon",
								"schtroumpf-bouchon");
					}
				}
			}
			System.out.println(this + " : " + mess);
			
			
		}
	}
	
	/**
	 * Red�finition de la m�thode equal pour comparer
	 *  deux instances de schtroumpf.(non-Javadoc).
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public final boolean equals(final Object o) {
		return (o instanceof Schtroumpf) && ((Schtroumpf) o) .nom.equals(nom);
	}
	/**
	 * Red�finition de la m�thode hashcode servant � 
	 * diff�rencier les Schtroumpfs contenu dans un Set(non-Javadoc).
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode() {
		return Objects.hash(nom);
	}

	/**
	 * Retourne le stock de SalsePareille d'un schtroumpf.
	 * @return StockDeSalsePareille
	 */
	public final int getStockDeSalsepareille() {
		return stockDeSalsePareille;
	}
	
	/**
	 * Setter de StrockDeSalsePareille.
	 * @param nouveauStock Nouveau Stock de SalsePareille.
	 */
	public final void setStockDeSalsepareille(final int nouveauStock) {
		stockDeSalsePareille = nouveauStock;
	}
	
	/**
	 * Renvoie le nom du Schtroumpf.
	 * @return nom.
	 */
	public final String getNom() {
		return nom;
	}

	/**
	 * Renvoie l'humeur du Schtroumpf.
	 * @return humeur.
	 */
	public final int getHumeur() {
		return humeur;
	}
	
	/**
	 * Setter d'humeur.
	 * @param nouvelleHumeur Nouvelle humeur du Schtroumpf
	 */
	public final void setHumeur(final int nouvelleHumeur) {
		humeur = nouvelleHumeur;
	}

	/**
	 * Renvoie la zone du Schtroumpf.
	 * @return zone
	 */
	public final Zone getZone() {
		return zone;
	}
	/**
	 * Setter de zone.
	 * @param newzone Nouvelle zone du Schtroumpf
	 */
	public final void setZone(final Zone newzone) {
		zone = newzone;
	}



}
