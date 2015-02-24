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
		int totalMange = 0;
		
		for (Schtroumpf s : village) {
			s.recolteDeLaSalsepareille();
			int stockInde = s.getStockDeSalsepareille();
			Random rand = new Random(); 
			int quantiteMangeParSchtroumpf = rand.nextInt(stockInde) + 1; 
			totalMange += quantiteMangeParSchtroumpf;
		}
		return totalMange;
	}
	
	
	public Set<Schtroumpf> tousLesHabitants() {
		return village;
	}
	
	
}
