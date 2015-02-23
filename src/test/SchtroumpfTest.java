/**
 * Classes de test des classes du package schtroumpfs
 */
package test;

import static org.junit.Assert.*;

import schtroumpfs.NomIncorrectException;
import schtroumpfs.Schtroumpf;

/**
 * Classe de test de la classe schtroumpf.Schtroumpf
 * @author L. Torres
 */
public class SchtroumpfTest extends AbstractSchtroumpfTest {
	
	@Override
	protected Schtroumpf creerSchtroumpf(String nom) throws NomIncorrectException {
		return new Schtroumpf( nom);
	}

	@Override
	protected void testNouvelleHumeur( int nouvelleHumeur, Schtroumpf s) {
		if (nouvelleHumeur > Schtroumpf.HUMEUR_MAX)
			assertEquals( Schtroumpf.HUMEUR_MAX, s.getHumeur());		
		else if (nouvelleHumeur < Schtroumpf.HUMEUR_MIN)
			assertEquals( Schtroumpf.HUMEUR_MIN, s.getHumeur());
		else
			assertEquals( nouvelleHumeur, s.getHumeur());		
	}
	
	@Override
	protected String emoticone(Schtroumpf s) {
		return s.deBonneHumeur()?":)":":(";
	}
	
}
