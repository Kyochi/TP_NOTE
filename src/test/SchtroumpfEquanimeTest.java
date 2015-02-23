/**
 * Classes de test des classes du package schtroumpfs
 */
package test;

import static org.junit.Assert.*;

import schtroumpfs.NomIncorrectException;
import schtroumpfs.Schtroumpf;
import schtroumpfs.SchtroumpfEquanime;

/**
 * Classe de test de la classe schtroumpf.SchtroumpfEquanime
 * @author L. Torres
 */
public class SchtroumpfEquanimeTest extends AbstractSchtroumpfTest {
	
	private static int nb = 0;
	
	@Override
	protected Schtroumpf creerSchtroumpf(String nom) throws NomIncorrectException {
		int humeurParDefaut;
		if ( nb % 2 == 0) 
			humeurParDefaut = SchtroumpfEquanime.HUMEUR_MAX; 
		else 
			humeurParDefaut = SchtroumpfEquanime.HUMEUR_MIN;
		nb = (nb + 1) % 5;
		return new SchtroumpfEquanime( nom, humeurParDefaut);
	}
	
	@Override
	protected void testNouvelleHumeur(int nouvelleHumeur, Schtroumpf s) {
		if (( s.deBonneHumeur() && nouvelleHumeur >= bonneHumeurMinimale ) 
				|| (! s.deBonneHumeur() && nouvelleHumeur < bonneHumeurMinimale )) {
			if (nouvelleHumeur > Schtroumpf.HUMEUR_MAX)
				assertEquals( Schtroumpf.HUMEUR_MAX, s.getHumeur());		
			else if (nouvelleHumeur < Schtroumpf.HUMEUR_MIN)
				assertEquals( Schtroumpf.HUMEUR_MIN, s.getHumeur());
			else
				assertEquals( nouvelleHumeur, s.getHumeur());	
		}
		else if ( s.deBonneHumeur())
			assertEquals( bonneHumeurMinimale, s.getHumeur());
		else
			assertEquals( bonneHumeurMinimale-1, s.getHumeur());			
	}

	@Override
	protected String emoticone(Schtroumpf s) {
		return s.deBonneHumeur()?":D":":[";
	}

}
