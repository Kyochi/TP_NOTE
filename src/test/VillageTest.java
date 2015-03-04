package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import schtroumpfs.Filtre;
import schtroumpfs.NomIncorrectException;
import schtroumpfs.Schtroumpf;
import schtroumpfs.SchtroumpfEquanime;
import schtroumpfs.Village;
import schtroumpfs.VillageVideException;
import schtroumpfs.Zone;

public class VillageTest {
	
	public final static Filtre<Schtroumpf> tous = new Filtre<Schtroumpf> () {
		@Override
		public boolean accepte(Schtroumpf s) {
			return true;
		}
	};
	
	public final static Filtre<Schtroumpf>contents = new Filtre<Schtroumpf> () {
		@Override
		public boolean accepte(Schtroumpf s) {
			return s.deBonneHumeur();
		}
	};
	
	public final static Filtre<Schtroumpf>sudistes = new Filtre<Schtroumpf> () {
		@Override
		public boolean accepte(Schtroumpf s) {
			return s.getZone().equals( Zone.SUD);
		}
	};

	private Village villageDesSchtroumpfsVide;
	private Village villageDesSchtroumpfs;
	private ArrayList<Schtroumpf> schtroumpfs;
	Integer [] humeurs;
	private int sommeInitialeDesHumeurs;
	
	@Before
	public void setUp() throws Exception {
		villageDesSchtroumpfsVide = new Village();
		
		humeurs = new Integer [] { 30, 25, 20, 10, 15 };
		sommeInitialeDesHumeurs = 30 + 25 + 20 + 10 + 15; 
		schtroumpfs = new ArrayList<Schtroumpf> ();
		schtroumpfs.add( new SchtroumpfEquanime("Grand Schtroumpf", 30));
		schtroumpfs.add( new SchtroumpfEquanime("Schtroumpfette", 25));
		schtroumpfs.add( new SchtroumpfEquanime("Schtroumpf farceur", 20));
		schtroumpfs.add( new SchtroumpfEquanime("Schtroumpf grognon", 10));
		schtroumpfs.add( new SchtroumpfEquanime("Schtroumpf bricoleur", 15));
		villageDesSchtroumpfs = new Village();
		villageDesSchtroumpfs.emmenage( schtroumpfs.get(0), Zone.SUD);
		villageDesSchtroumpfs.emmenage( schtroumpfs.get(1), Zone.NORD);
		villageDesSchtroumpfs.emmenage( schtroumpfs.get(2), Zone.SUD);
		villageDesSchtroumpfs.emmenage( schtroumpfs.get(3), Zone.NORD);
		villageDesSchtroumpfs.emmenage( schtroumpfs.get(4), Zone.SUD);		
		assertEquals( 30, schtroumpfs.get(0).getHumeur());
		assertEquals( 25, schtroumpfs.get(1).getHumeur());
		assertEquals( 20, schtroumpfs.get(2).getHumeur());
		assertEquals( 10, schtroumpfs.get(3).getHumeur());
		assertEquals( 15, schtroumpfs.get(4).getHumeur());		
	}

	@After
	public void tearDown() throws Exception {
		villageDesSchtroumpfsVide = null;
		villageDesSchtroumpfs = null;
	}

	@Test
	public final void testVillage() {
		assertEquals(0, villageDesSchtroumpfsVide.tousLesHabitants().size());
	}

	@Test
	public final void testEmmenage() throws NomIncorrectException {
		Set<Schtroumpf> tous = villageDesSchtroumpfs.tousLesHabitants();
		assertEquals(5, tous.size());
		assertTrue( tous.contains( schtroumpfs.get(0)));
		assertTrue( tous.contains( schtroumpfs.get(1)));
		assertTrue( tous.contains( schtroumpfs.get(2)));
		assertTrue( tous.contains( schtroumpfs.get(3)));
		assertTrue( tous.contains( schtroumpfs.get(4)));
		assertTrue( tous.contains( new Schtroumpf("Grand Schtroumpf")));
		assertTrue( tous.contains( new Schtroumpf("Schtroumpfette")));
		assertTrue( tous.contains( new Schtroumpf("Schtroumpf farceur")));
		assertTrue( tous.contains( new Schtroumpf("Schtroumpf bricoleur")));
		assertTrue( tous.contains( new Schtroumpf("Schtroumpf grognon")));
		assertEquals( 5, tous.size());
		villageDesSchtroumpfs.emmenage( new Schtroumpf("Grand Schtroumpf"), Zone.SUD);
		assertEquals( 5, tous.size());
		villageDesSchtroumpfs.emmenage( new Schtroumpf("Grand Schtroumpf"), Zone.NORD);
		assertEquals( 5, tous.size());
	}

	@Test
	public final void testOrganiserRepasDeFete() {
		int ancienStockDeSalsepareille = 0;
		int nouveauStockDeSalsepareille = 0;
		for ( Schtroumpf s : schtroumpfs)
			ancienStockDeSalsepareille += s.getStockDeSalsepareille();
		int quantiteMangee = villageDesSchtroumpfs.organiserRepasDeFete();
		for ( Schtroumpf s : schtroumpfs)
			nouveauStockDeSalsepareille += s.getStockDeSalsepareille();
		assertTrue( quantiteMangee >=5 && quantiteMangee <= 5*Schtroumpf.APPETIT_MAX);
		assertEquals( ancienStockDeSalsepareille - nouveauStockDeSalsepareille, quantiteMangee);
		assertEquals( Math.min(Schtroumpf.HUMEUR_MAX, 30+Schtroumpf.REVIGORATION), schtroumpfs.get(0).getHumeur());
		assertEquals( Math.min(Schtroumpf.HUMEUR_MAX, 25+Schtroumpf.REVIGORATION), schtroumpfs.get(1).getHumeur());
		assertEquals( Math.min(Schtroumpf.HUMEUR_MAX, 20+Schtroumpf.REVIGORATION), schtroumpfs.get(2).getHumeur());
		assertEquals( Math.min((Schtroumpf.HUMEUR_MAX+Schtroumpf.HUMEUR_MIN)/2-1, 10+Schtroumpf.REVIGORATION), schtroumpfs.get(3).getHumeur());
		assertEquals( Math.min((Schtroumpf.HUMEUR_MAX+Schtroumpf.HUMEUR_MIN)/2-1, 15+Schtroumpf.REVIGORATION), schtroumpfs.get(4).getHumeur());
	}

	@Test
	public final void testOrganiserRecolte1() {
		int quantiteRecoltee, quantiteRecolteeAttendue = 0;
		for (Schtroumpf s : schtroumpfs)
			quantiteRecolteeAttendue += s.getHumeur() * Schtroumpf.RECOLTE_MAX / Schtroumpf.HUMEUR_MAX;
		quantiteRecoltee = villageDesSchtroumpfs.organiserRecolte(tous);
		assertEquals( quantiteRecolteeAttendue, quantiteRecoltee);
		assertEquals( Math.max((Schtroumpf.HUMEUR_MAX+Schtroumpf.HUMEUR_MIN)/2 ,30-Schtroumpf.FATIGUE), schtroumpfs.get(0).getHumeur());
		assertEquals( Math.max((Schtroumpf.HUMEUR_MAX+Schtroumpf.HUMEUR_MIN)/2 ,25-Schtroumpf.FATIGUE), schtroumpfs.get(1).getHumeur());
		assertEquals( Math.max((Schtroumpf.HUMEUR_MAX+Schtroumpf.HUMEUR_MIN)/2 ,20-Schtroumpf.FATIGUE), schtroumpfs.get(2).getHumeur());
		assertEquals( Math.max(Schtroumpf.HUMEUR_MIN, 10-Schtroumpf.FATIGUE), schtroumpfs.get(3).getHumeur());
		assertEquals( Math.max(Schtroumpf.HUMEUR_MIN,15-Schtroumpf.FATIGUE), schtroumpfs.get(4).getHumeur());
	}
	
	@Test
	public final void testOrganiserRecolte2() {
		int quantiteRecoltee, quantiteRecolteeAttendue;
		quantiteRecoltee = villageDesSchtroumpfs.organiserRecolte( sudistes);
		quantiteRecolteeAttendue = (30 * Schtroumpf.RECOLTE_MAX / Schtroumpf.HUMEUR_MAX)
				+ (20 * Schtroumpf.RECOLTE_MAX / Schtroumpf.HUMEUR_MAX)
				+ (15 * Schtroumpf.RECOLTE_MAX / Schtroumpf.HUMEUR_MAX);
		assertEquals( quantiteRecolteeAttendue, quantiteRecoltee);
		assertEquals( Math.max((Schtroumpf.HUMEUR_MAX+Schtroumpf.HUMEUR_MIN)/2 ,30-Schtroumpf.FATIGUE), schtroumpfs.get(0).getHumeur());
		assertEquals( 25 , schtroumpfs.get(1).getHumeur());
		assertEquals( Math.max((Schtroumpf.HUMEUR_MAX+Schtroumpf.HUMEUR_MIN)/2 ,20-Schtroumpf.FATIGUE), schtroumpfs.get(2).getHumeur());
		assertEquals( 10, schtroumpfs.get(3).getHumeur());
		assertEquals( Math.max(Schtroumpf.HUMEUR_MIN,15-Schtroumpf.FATIGUE), schtroumpfs.get(4).getHumeur());
	}
	
	@Test
	public final void testOrganiserRecolte3() {
		int quantiteRecoltee, quantiteRecolteeAttendue;
		quantiteRecoltee = villageDesSchtroumpfs.organiserRecolte( contents);
		quantiteRecolteeAttendue = 30 * Schtroumpf.RECOLTE_MAX / Schtroumpf.HUMEUR_MAX
				+ 25 * Schtroumpf.RECOLTE_MAX / Schtroumpf.HUMEUR_MAX
				+ 20 * Schtroumpf.RECOLTE_MAX / Schtroumpf.HUMEUR_MAX;
		assertEquals( quantiteRecolteeAttendue, quantiteRecoltee);
		assertEquals( quantiteRecolteeAttendue, quantiteRecoltee);
		assertEquals( Math.max((Schtroumpf.HUMEUR_MAX+Schtroumpf.HUMEUR_MIN)/2 ,30-Schtroumpf.FATIGUE), schtroumpfs.get(0).getHumeur());
		assertEquals( Math.max((Schtroumpf.HUMEUR_MAX+Schtroumpf.HUMEUR_MIN)/2 ,25-Schtroumpf.FATIGUE), schtroumpfs.get(1).getHumeur());
		assertEquals( Math.max((Schtroumpf.HUMEUR_MAX+Schtroumpf.HUMEUR_MIN)/2 ,20-Schtroumpf.FATIGUE), schtroumpfs.get(2).getHumeur());
		assertEquals( 10, schtroumpfs.get(3).getHumeur());
		assertEquals( 15, schtroumpfs.get(4).getHumeur());
	}

	@Test(expected=VillageVideException.class)
	public final void testIndiceDeBonheurBrut1() throws VillageVideException {
		villageDesSchtroumpfsVide.indiceDeBonheurBrut();
	}
	
	@Test
	public final void testIndiceDeBonheurBrut2() throws VillageVideException {
		assertEquals( (sommeInitialeDesHumeurs)/(float)schtroumpfs.size() , villageDesSchtroumpfs.indiceDeBonheurBrut(), 0.01);
	}

	@Test(expected=VillageVideException.class)
	public final void testPIBParSchtroumpf1() throws VillageVideException {
		villageDesSchtroumpfsVide.produitInterieurBrutParSchtroumpf();
	}
	
	@Test
	public final void testPIBParSchtroumpf() throws VillageVideException{
		int stockDeSalsepareille = 0;
		for ( Schtroumpf s : schtroumpfs)
			stockDeSalsepareille += s.getStockDeSalsepareille();
		assertEquals( stockDeSalsepareille/5.0 , villageDesSchtroumpfs.produitInterieurBrutParSchtroumpf(), 0.01);
	}
	
	@Test
	public final void testListeDesHabitantsTelsQue() {
		assertEquals( 0, villageDesSchtroumpfsVide.listeDesHabitantsTelsQue(tous).size());
		assertEquals( 0, villageDesSchtroumpfsVide.listeDesHabitantsTelsQue(sudistes).size());
		assertEquals( 0, villageDesSchtroumpfsVide.listeDesHabitantsTelsQue(contents).size());
		List<Schtroumpf> liste = villageDesSchtroumpfs.listeDesHabitantsTelsQue(tous);
		assertEquals( 5, liste.size());
		for ( Schtroumpf s : schtroumpfs)
			assertTrue( liste.contains(s));
		liste = villageDesSchtroumpfs.listeDesHabitantsTelsQue(sudistes);
		assertEquals( 3, liste.size());
		for ( Schtroumpf s : schtroumpfs)
			if (s.getZone().equals(Zone.SUD))
				assertTrue( liste.contains(s));
			else
				assertFalse( liste.contains(s));
		liste = villageDesSchtroumpfs.listeDesHabitantsTelsQue(contents);
		assertEquals( 3, liste.size());
		for ( Schtroumpf s : schtroumpfs)
			if (s.deBonneHumeur())
				assertTrue( liste.contains(s));
			else
				assertFalse( liste.contains(s));
	}

	@Test
	public final void testListeLesHabitantsParHumeurCroissante() {
		List<Schtroumpf> liste;
		liste = villageDesSchtroumpfsVide.listeLesHabitantsParHumeurCroissante();
		assertTrue( liste.isEmpty());
		liste = villageDesSchtroumpfs.listeLesHabitantsParHumeurCroissante();
		assertEquals( 5, liste.size());
		assertEquals( 10, liste.get(0).getHumeur());
		assertEquals( 15, liste.get(1).getHumeur());
		assertEquals( 20, liste.get(2).getHumeur());
		assertEquals( 25, liste.get(3).getHumeur());
		assertEquals( 30, liste.get(4).getHumeur());
	}

}
