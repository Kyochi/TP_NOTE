package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import schtroumpfs.MorsureException;
import schtroumpfs.NomIncorrectException;
import schtroumpfs.Schtroumpf;
import schtroumpfs.Zone;

/**
 * Classe abstraite qui definit le code commun des classes de test
 * SchtroumpfTest et SchtroumpfEquanimeTest.
 * @author L. Torres
 */
public abstract class AbstractSchtroumpfTest {
	private static OutputStream sortie; // flux de redirection de la sortie
	private static PrintStream original; // flux de sortie initial
	private static String lineSeparator; // saut de ligne
	private static int indiceAffichage; // variable utilisee par la methode dernierAffichage
	protected static int bonneHumeurMinimale = (Schtroumpf.HUMEUR_MAX+Schtroumpf.HUMEUR_MIN)/2;
	
	protected ArrayList<Schtroumpf> schtroumpfs; // liste de Schtroumpfs
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		original = System.out;
		sortie = new ByteArrayOutputStream();
		System.setOut( new PrintStream(sortie));
		lineSeparator = System.lineSeparator();
		indiceAffichage = 0;
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.setOut( original);
		original = null;
		sortie = null;
	}
	
	/* 
	 * Renvoie le texte qui vient d'etre affiche sur la sortie 
	 * depuis le dernier appel de la methode 
	 * */
	protected String dernierAffichage() {
		String resultat = sortie.toString().substring(indiceAffichage);
		indiceAffichage = sortie.toString().length();
		return resultat ;
	}
	
	abstract protected Schtroumpf creerSchtroumpf(String nom) throws NomIncorrectException;
	
	@Before
	public void setUp() throws Exception {
		schtroumpfs = new ArrayList<Schtroumpf>();
		schtroumpfs.add( creerSchtroumpf("Grand Schtroumpf"));
		schtroumpfs.add( creerSchtroumpf("Schtroumpfette"));
		schtroumpfs.add( creerSchtroumpf("Schtroumpf farceur"));
		schtroumpfs.add( creerSchtroumpf("Schtroumpf grognon"));
		schtroumpfs.add( creerSchtroumpf("Schtroumpf bricoleur"));
	}
	
	/**
	 * Methode pour le test des constructeurs {@link schtroumpfs.Schtroumpf#Schtroumpf(java.lang.String)}
	 * et {@link schtroumpfs.SchtroumpfEquanime#SchtroumpfEquanime(java.lang.String,int)}.
	 * @throws NomIncorrectException 
	 */
	@Test(expected=NomIncorrectException.class)
	public final void testConstructeur1() throws NomIncorrectException {
		creerSchtroumpf("le schtroumpf bricoleur");
	}
	
	/**
	 * Methode pour le test des constructeurs {@link schtroumpfs.Schtroumpf#Schtroumpf(java.lang.String)}
	 * et {@link schtroumpfs.SchtroumpfEquanime#SchtroumpfEquanime(java.lang.String,int)}.
	 * @throws NomIncorrectException 
	 */
	@Test(expected=NomIncorrectException.class)
	public final void testConstructeur2() throws NomIncorrectException {
		creerSchtroumpf("schtroumpf bricoleur");
	}	
	
	/**
	 * Methode pour le test des constructeurs {@link schtroumpfs.Schtroumpf#Schtroumpf(java.lang.String)}
	 * et {@link schtroumpfs.SchtroumpfEquanime#SchtroumpfEquanime(java.lang.String,int)}.
	 */
	@Test
	public final void testConstructeur3() {
		for (Schtroumpf s : schtroumpfs) { 
			assertTrue( s.getHumeur() >= Schtroumpf.HUMEUR_MIN && s.getHumeur() <= Schtroumpf.HUMEUR_MAX);
			assertFalse ( s.estMalade());
			assertEquals( 0, s.getStockDeSalsepareille() );
			assertEquals( Zone.SUD, s.getZone() );
		}
	}
	
	/**
	 * Methode pour le test de {@link schtroumpfs.Schtroumpf#getZone()}
	 * et {@link schtroumpfs.Schtroumpf#setZone()}.
	 */
	@Test
	public final void testGetSetZone() {
		assertEquals( Zone.SUD, schtroumpfs.get(0).getZone() );
		schtroumpfs.get(0).setZone( Zone.NORD);
		assertEquals( Zone.NORD, schtroumpfs.get(0).getZone() );
		schtroumpfs.get(0).setZone( Zone.SUD);
		assertEquals( Zone.SUD, schtroumpfs.get(0).getZone() );		
		assertEquals( Zone.SUD, schtroumpfs.get(1).getZone() );
		schtroumpfs.get(1).setZone( Zone.NORD);
		assertEquals( Zone.NORD, schtroumpfs.get(1).getZone() );
	}

	/**
	 * Methode pour le test de {@link schtroumpfs.Schtroumpf#deBonneHumeur()}.
	 */
	@Test
	public final void testDeBonneHumeur() {
		for (Schtroumpf s : schtroumpfs) { 
			assertEquals( s.deBonneHumeur(), s.getHumeur() >= bonneHumeurMinimale);
		}
	}
	
	protected abstract void testNouvelleHumeur( int nouvelleHumeur, Schtroumpf s);

	/**
	 * Methode pour le test de {@link schtroumpfs.Schtroumpf#recolteDeLaSalsepareille()}.
	 */
	@Test
	public final void testRecolteDeLaSalsepareille() {
		int ancienneHumeur, ancienStock, quantiteRecoltee;
		for (Schtroumpf s : schtroumpfs) { 
			ancienneHumeur = s.getHumeur();
			ancienStock = s.getStockDeSalsepareille();
			quantiteRecoltee = s.recolteDeLaSalsepareille();
			assertEquals( Schtroumpf.RECOLTE_MAX * ancienneHumeur / Schtroumpf.HUMEUR_MAX, quantiteRecoltee);
			assertEquals( ancienStock + quantiteRecoltee, s.getStockDeSalsepareille());
			testNouvelleHumeur( ancienneHumeur - Schtroumpf.FATIGUE, s);
		}
		Schtroumpf s = schtroumpfs.get(0);
		for ( int i = 0; i < Schtroumpf.HUMEUR_MAX - Schtroumpf.HUMEUR_MIN + 1; i++) {
			ancienneHumeur = s.getHumeur();
			s.recolteDeLaSalsepareille();
			testNouvelleHumeur( ancienneHumeur - Schtroumpf.FATIGUE, s);
		}
	}

	/**
	 * Methode pour le test de {@link schtroumpfs.Schtroumpf#mangeDeLaSalsepareille()}.
	 */
	@Test
	public final void testMangeDeLaSalsepareille() {
		int ancienneHumeur, ancienStock, quantiteMangee;
		for (Schtroumpf s : schtroumpfs) { 
			ancienneHumeur = s.getHumeur();
			ancienStock = s.getStockDeSalsepareille();
			quantiteMangee = s.mangeDeLaSalsepareille();
			assertTrue( quantiteMangee >= 1 && quantiteMangee <= Schtroumpf.APPETIT_MAX);
			assertEquals( ancienStock - quantiteMangee, s.getStockDeSalsepareille());
			testNouvelleHumeur( ancienneHumeur + Schtroumpf.REVIGORATION, s);
		}
		Schtroumpf s = schtroumpfs.get(1);
		for ( int i = 0; i < Schtroumpf.HUMEUR_MAX - Schtroumpf.HUMEUR_MIN + 1; i++) {
			ancienneHumeur = s.getHumeur();
			s.mangeDeLaSalsepareille();
			testNouvelleHumeur( ancienneHumeur + Schtroumpf.REVIGORATION, s);
		}
	}
	
	/**
	 * Methode pour le test de {@link schtroumpfs.Schtroumpf#mangeDuPollenDeTubereuse()}.
	 */
	@Test
	public final void testMangeDuPollenDeTubereuse() {
		Schtroumpf.attaqueDeLaMoucheBzz( schtroumpfs);
		for (Schtroumpf s : schtroumpfs) { 
			if ( s.estMalade()) {
				s.mangeDuPollenDeTubereuse();
				testNouvelleHumeur( Schtroumpf.HUMEUR_MAX, s);
			} else {
				int humeur = s.getHumeur();
				s.mangeDuPollenDeTubereuse();
				testNouvelleHumeur( humeur, s);
			}
				
		}
		for (Schtroumpf s : schtroumpfs) { 
			assertFalse ( s.estMalade());
		}
	}
		
	/**
	 * Methode pour le test de {@link schtroumpfs.Schtroumpf#attaqueDeLaMoucheBzz(java.util.Collection)}.
	 */
	@Test
	public final void testAttaqueDeLaMoucheBzz() {
		int ancienNombreDeMalades = 0, nouveauNombreDeMalades;
		Schtroumpf malade;
		for (Schtroumpf s : schtroumpfs) { 
			if ( s.estMalade()) ancienNombreDeMalades++;
		}
		nouveauNombreDeMalades = ancienNombreDeMalades;
		malade = Schtroumpf.attaqueDeLaMoucheBzz( schtroumpfs);
		testNouvelleHumeur( Schtroumpf.HUMEUR_MIN, malade);	
		for (Schtroumpf s : schtroumpfs) { 
			if ( s.estMalade()) nouveauNombreDeMalades++;
		}
		assertEquals( 1, nouveauNombreDeMalades - ancienNombreDeMalades);
	}
		
	/**
	 * Methode pour le test de {@link schtroumpfs.Schtroumpf#mord(schtroumpfs.Schtroumpf)}.
	 * @throws MorsureException 
	 */
	@Test()
	public final void testMord() throws MorsureException {
		for (Schtroumpf s : schtroumpfs) { 
			assertFalse( s.estMalade());
			try { s.mord( schtroumpfs.get(0)); } 
			catch (MorsureException e) {
				if ( s.equals(schtroumpfs.get(0)))
					assertEquals( s + " ne peut pas se mordre lui-meme", e.getMessage() );
				else {
					assertEquals( s.toString() + " n'est pas malade", e.getMessage() );
				}
			}
		}
		Schtroumpf malade = Schtroumpf.attaqueDeLaMoucheBzz( schtroumpfs);
		for (Schtroumpf s : schtroumpfs) { 
			if (! malade.equals( s) ) {
				malade.mord( s);
				assertEquals( s.toString() + " : Aie !" + lineSeparator, dernierAffichage());
				assertTrue (s.estMalade());
				testNouvelleHumeur( Schtroumpf.HUMEUR_MIN, s);
				//assertEquals (Schtroumpf.HUMEUR_MIN, s.getHumeur());
			}
		}
	}

	/**
	 * Methode pour le test de {@link schtroumpfs.Schtroumpf#parle(java.lang.String)}.
	 */
	@Test
	public final void testParle() {
		Schtroumpf.attaqueDeLaMoucheBzz( schtroumpfs);
		String message1 = "Bonjour les amis. Venez vite car la Schtroumpfette nous appelle !";
		String message2 = "Je peux expliquer le logiciel libre en trois mots : Liberte , Egalite , Fraternite .";
		String message3 = "a bb ccc dddd eeeee ffffff Grand Schtroump, Schtroumpfette et Schtroumpf gggggggggg";
		String message4 = "J'ai besoin d'un tire-bouchon .";
		for (Schtroumpf s : schtroumpfs) {
			s.parle( message1);
			if (s.estMalade()) 
				assertEquals( s.toString() + " : GNAP !" + lineSeparator, dernierAffichage() );
			else
				assertEquals( s.toString() + " : Bonjour les amis. Venez vite car la Schtroumpfette nous schtroumpf !"  + lineSeparator, dernierAffichage() );
			s.parle( message2);
			if (s.estMalade()) 
				assertEquals( s.toString() + " : GNAP !" + lineSeparator, dernierAffichage() );
			else
				assertEquals( s.toString() + " : Je peux schtroumpf le schtroumpf libre en trois mots : schtroumpf , schtroumpf , schtroumpf ." + lineSeparator, dernierAffichage() );
			s.parle( message3);
			if (s.estMalade()) 
				assertEquals( s.toString() + " : GNAP !" + lineSeparator, dernierAffichage() );
			else
				assertEquals( s.toString() + " : a bb ccc dddd eeeee schtroumpf Grand Schtroump, Schtroumpfette et Schtroumpf gggggggggg" + lineSeparator, dernierAffichage() );
			s.parle( message4);
			if (s.estMalade()) 
				assertEquals( s.toString() + " : GNAP !" + lineSeparator, dernierAffichage() );
			else {
				s.setZone( Zone.SUD);
				assertEquals( s.toString() + " : J'ai schtroumpf d'un schtroumpf-bouchon ." + lineSeparator, dernierAffichage() );
				s.setZone( Zone.NORD);
				s.parle( message4);
				assertEquals( s.toString() + " : J'ai schtroumpf d'un tire-schtroumpf ." + lineSeparator, dernierAffichage() );
			}
		}
	}
	
	protected abstract String emoticone(Schtroumpf s);
	
	/**
	 * Methode pour le test de {@link schtroumpfs.Schtroumpf#toString()}.
	 */
	@Test
	public final void testToString() {
		Schtroumpf courant = schtroumpfs.get(0);
		assertEquals( "Grand Schtroumpf["+courant.getHumeur()
				+"]"+emoticone(courant), courant.toString());
		courant = schtroumpfs.get(1);
		assertEquals( "Schtroumpfette["+courant.getHumeur()
				+"]"+emoticone(courant), courant.toString());
		courant = schtroumpfs.get(2);
		assertEquals( "Schtroumpf farceur["+courant.getHumeur()
				+"]"+emoticone(courant), courant.toString());
		courant = schtroumpfs.get(3);
		assertEquals( "Schtroumpf grognon["+courant.getHumeur()
				+"]"+emoticone(courant), courant.toString());
		courant = schtroumpfs.get(4);
		assertEquals( "Schtroumpf bricoleur["+courant.getHumeur()
				+"]"+emoticone(courant), courant.toString());
	}

}
