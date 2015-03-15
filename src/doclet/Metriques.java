package doclet;

import com.sun.javadoc.Doclet;
import com.sun.javadoc.RootDoc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.ConstructorDoc;
import com.sun.javadoc.ProgramElementDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.ExecutableMemberDoc;
import com.sun.javadoc.Tag;

public class Metriques extends Doclet {


	public static boolean start(RootDoc root) { 
		ClassDoc[] classes = root.classes();
		FieldDoc[] champs;
		
		int nombreDeClassesPrivee = 0;
		int nombreDeClassesPublique = 0;
		int nombreMoyenDeMethodeParClassePublique = 0;
		int nombreMoyenDeCommentaireParMembreDeClassePublique = 0;
		
		for (int i = 0; i < classes.length; ++i) {
			System.out.println();	
			if (classes[i].isPublic()) {
				nombreDeClassesPublique++;
		        nombreMoyenDeMethodeParClassePublique += (classes[i].methods()).length;
		        champs = classes[i].fields();
		        for (int j = 0; j < champs.length; ++j) {
			        if (champs[i].isPublic()) nombreMoyenDeCommentaireParMembreDeClassePublique += (champs[i].tags()).length;   
		        }
			}
			
			if (classes[i].isPrivate()) nombreDeClassesPrivee++;
				
		}
		nombreMoyenDeCommentaireParMembreDeClassePublique /= classes.length;
		nombreMoyenDeMethodeParClassePublique /= nombreDeClassesPublique;
		System.out.println("Le nombre de classe concrète publique est de " + nombreDeClassesPublique + ".");
		System.out.println("\t" + "- Le nombre moyen de méthode par classe concrète publique est de " + nombreMoyenDeMethodeParClassePublique +  ".");
		System.out.println("\t" + "- Le nombre moyen de commentaire javadoc par membre de classe concrète publique est de " + nombreMoyenDeCommentaireParMembreDeClassePublique + ".");
		
		System.out.println("Le nombre de classe concrète privée est de " + nombreDeClassesPrivee + ".");
		
		
		
		
		return true;
	
		
	}
}
