package schtroumpfs;

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
		return 0;
	}
	public int indiceDeBonheurBrut() {
		return 1;
	}
	public Double produitInterieurBrutParSchtroumpf() {
		return 1.1;
	}
	public List<Schtroumpf>listeDesHabitantsTelsQue(Filtre<Schtroumpf> f ) {
		return (List<Schtroumpf>) village;
	}
	public List<Schtroumpf>listeLesHabitantsParHumeurCroissante() {
		return (List<Schtroumpf>) village;
	}
	public Set<Schtroumpf> tousLesHabitants() {
		return village;
	}
	
	
}
