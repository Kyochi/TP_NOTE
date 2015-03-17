package schtroumpfs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Classe repr�sentant un village de Schtroumpfs.
 * @author Baptiste CHARRIER
 * @version 1
 */
public class Village {

	/**
	 * Variable contenant le village de Schtroumpf dans un Set.
	 */
	private Set<Schtroumpf> village;
	
	/**
	 * Contructeur de Village.
	 * Initialise le Set village.
	 */
	public Village() {
		village = new HashSet<Schtroumpf>();
	}
	
	/**
	 * Permet � un Schtroumpf d'emm�nager dans une zone du village.
	 * @param s Schtroumpf arrivant.
	 * @param z Zone d'emm�nagement.
	 */
	public final void emmenage(final Schtroumpf s, final Zone z) {
		s.setZone(z);
		village.add(s);
	}
	
	/**
	 * Organise un repas de f�te pour tout les habitants du village.
	 * @return Salse consomm�es pendant le repas.
	 */
	public final int organiserRepasDeFete() {
		int salseConso = 0;
		for (Schtroumpf s : village) {
			salseConso += s.mangeDeLaSalsepareille();
		}
		return salseConso;
	}
	
	/**
	 * Organise une r�colte commune � tout les habitants.
	 * @param f Filtre acceptant ou 
	 * non la participation d'un schtroumpf du village.
	 * @return Quantit�e de salse recolt�es.
	 */
	public final int organiserRecolte(final Filtre<Schtroumpf> f) {
		int salseRecolte = 0;
		for (Schtroumpf s: village) { 
			if (f.accepte(s)) {
				salseRecolte += s.recolteDeLaSalsepareille(); 
			}
		}
		return salseRecolte;
	}
	/**
	 * Calcule l'indice de bonheur brut d'un village.
	 * @return Indice de bonheur brut 
	 * (heure moyenne des habitants) d'un village.
	 * @throws VillageVideException L�ve une exception si le village est vide.
	 */
	public final float indiceDeBonheurBrut() throws VillageVideException {
		if (village.isEmpty()) {
			throw new VillageVideException("Impossible de calculer l'IBB,"
					+ " le village est vide");
		} else {
			float sommeDesHumeurs = 0;
			for (Schtroumpf s : village) { 
				sommeDesHumeurs += 
						((SchtroumpfEquanime) s).getHumeurInitiale(); 
			}
			return (sommeDesHumeurs) / (float) village.size();
		}
	}
	
	/**
	 * Calcule le produit int�rieur brut du village.
	 * @return PIB (Stock de salse moyen par habitant) du village.
	 * @throws VillageVideException L�ve une exception si le village est vide.
	 */
	public final Double produitInterieurBrutParSchtroumpf() 
			throws VillageVideException {
		if (village.isEmpty()) {
			throw new VillageVideException("Impossible de calculer le PIB,"
					+ " le village est vide");
		}
		else {
			double salseTotale = 0;
			for (Schtroumpf s : village) {
				salseTotale += s.getStockDeSalsepareille();
			}
			return salseTotale / village.size();
		}
	}
	
	/**
	 * Renvoie une Liste des habitants en fonction d'un Filtre.
	 * @param f Filtre � Schtroumpf.
	 * @return une liste de Schtroumpf.
	 */
	public final List<Schtroumpf> listeDesHabitantsTelsQue(final 
			Filtre<Schtroumpf> f) {
		List<Schtroumpf> listSchtroumpfTelQue = new ArrayList();
		for (Schtroumpf s : village) {
			if (f.accepte(s)) {
				listSchtroumpfTelQue.add(s); 
			}
		}
		return listSchtroumpfTelQue;
	}
	
	/**
	 * Renvoie une liste des habitants du village
	 *  tri�e par ordre d�croissant d'humeur.
	 * @return Liste tri�e de Schtroumpf.
	 */
	public final List<Schtroumpf> listeLesHabitantsParHumeurCroissante() {
		List<Schtroumpf> listSorted = new ArrayList(village);
		Collections.sort(listSorted, new Comparator<Schtroumpf>() {
			public int compare(final Schtroumpf s1, final Schtroumpf s2) {
				if (s1.getHumeur() <= s2.getHumeur()) {
					return -1;
				} else { 
					return 1; 
				}
			}
		});
		return listSorted;
	}
	/**
	 * Renvoie tous les habitants du village.
	 * @return Renvoie le village des Schtroumpfs
	 */
	public final Set<Schtroumpf> tousLesHabitants() {
		return village;
	}

}
