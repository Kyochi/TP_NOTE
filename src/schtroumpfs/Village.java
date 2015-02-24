package schtroumpfs;

import java.util.ArrayList;
import java.util.HashSet;
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
	
	
	public ArrayList<Schtroumpf> tousLesHabitants() {
		return village;
	}
	
	
}
