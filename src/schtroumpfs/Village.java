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
			int stockInde = s.getStockDeSalsepareille();
			
			int quantiteMangeParSchtroumpf =0;
			if (stockInde > Schtroumpf.APPETIT_MAX) {
				Random rand = new Random(); 
				quantiteMangeParSchtroumpf = rand.nextInt(Schtroumpf.APPETIT_MAX) + 1; 
			}
			if (stockInde <= Schtroumpf.APPETIT_MAX) {
				Random rand = new Random(); 
				quantiteMangeParSchtroumpf = rand.nextInt(stockInde) + 1; 
			}
			s.setStockDeSalsepareille(stockInde - quantiteMangeParSchtroumpf);
			totalMange += quantiteMangeParSchtroumpf;
		}
		return totalMange;
	}
	
	
	public Set<Schtroumpf> tousLesHabitants() {
		return village;
	}
	
	
}
