package schtroumpfs;

import java.util.HashSet;
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
	
	
	public Set<Schtroumpf> tousLesHabitants() {
		return village;
	}
	
	
}
