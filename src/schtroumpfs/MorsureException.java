package schtroumpfs;

public class MorsureException extends Exception {
	public MorsureException(Schtroumpf s) {
		System.out.println(s + "ne peut se mordre tout seul");
	}
}
