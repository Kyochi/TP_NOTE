package schtroumpfs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Village {

	private Set<Schtroumpf> village;
	
	public Village() {
		village = new HashSet<Schtroumpf>();
	}
	public void emmenage(Schtroumpf s, Zone z) {
		s.setZone(z);
		village.add(s);
	}
	public int organiserRepasDeFete() {
		int salseConso = 0;
		for (Schtroumpf s : village) {
			salseConso += s.mangeDeLaSalsepareille();
			
		}
		return salseConso;
		
	}
	public int organiserRecolte(Filtre<Schtroumpf> f) {
		int salseConso = 0;
		for (Schtroumpf s: village) {
			if (f.accepte(s)) salseConso += s.recolteDeLaSalsepareille();
			
		}
		return salseConso;
	}
	public float indiceDeBonheurBrut() throws VillageVideException {
		if (village.isEmpty()) throw new VillageVideException("Impossible de calculer l'IBB, le village est vide");
		else {
			float sommeDesHumeurs = 0;
			for (Schtroumpf s : village) {
				sommeDesHumeurs += ((SchtroumpfEquanime) s).getHumeurInitiale();
				
			}
			return (sommeDesHumeurs)/(float)village.size();
		}
	}
	public Double produitInterieurBrutParSchtroumpf() throws VillageVideException {
		if(village.isEmpty()) throw new VillageVideException("Impossible de calculer le PIB, le village est vide");
		else {
			double salseTotale = 0;
			for (Schtroumpf s : village) {
				salseTotale += s.getStockDeSalsepareille();
			}
			return salseTotale/village.size();
		}
	}
	public List<Schtroumpf>listeDesHabitantsTelsQue(Filtre<Schtroumpf> f ) {
		List<Schtroumpf> listSchtroumpfTelQue = new ArrayList();
		for (Schtroumpf s : village) {
			if(f.accepte(s)) listSchtroumpfTelQue.add(s);
		}
		return listSchtroumpfTelQue;
		
	}
	public List<Schtroumpf>listeLesHabitantsParHumeurCroissante() {
		List<Schtroumpf>listSorted = new ArrayList(village);
		
		Collections.sort(listSorted, new Comparator<Schtroumpf>(){
			public int compare(Schtroumpf s1, Schtroumpf s2) {
				if(s1.getHumeur() <= s2.getHumeur()) return -1;
				else return 1;
			}
		});
		return listSorted;
	}
	
	public Set<Schtroumpf> tousLesHabitants() {
		return village;
	}
	

	
	
	
}
