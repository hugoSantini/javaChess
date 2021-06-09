//*


/**
 * Classe gérant la partie
 * 
 * @author Hugo Santini , Lucas Mennessier
 * @version 1.0
 */

package projet_echecs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Partie {

	/* -------------------------------------
	 *  Constante de classe
	 ------------------------------------- */

	private static final String[] plateauCase = {
	                              "a8","b8","c8","d8","e8","f8","g8","h8",
	                              "a7","b7","c7","d7","e7","f7","g7","h7",
	                              "a6","b6","c6","d6","e6","f6","g6","h6",
	                              "a5","b5","c5","d5","e5","f5","g5","h5",
	                              "a4","b4","c4","d4","e4","f4","g4","h4",
	                              "a3","b3","c3","d3","e3","f3","g3","h3",
	                              "a2","b2","c2","d2","e2","f2","g2","h2",
	                              "a1","b1","c1","d1","e1","f1","g1","h1"
	};

	/* -------------------------------------
	 *  Variable d'instance
	 ------------------------------------- */

	private int turnNumber;
	private Plateau plateau;
	private Scanner scan = new Scanner(System.in);
	
	/* -------------------------------------
	 *  Méthode de classe
	 ------------------------------------- */

	/**
	 * Test si la chaîne de caractère décrivant les coordonnées d'une case donnée en paramètre sont des coordonnées valides.
	 * 
	 * @param caseString les coordonnée de la case. Par exemple \"b2\", \"c6\"
	 * @return True si les coordonnées correspondent à une case du plateau et False sinon
	 */
	public static boolean testCaseDansPlateau(String caseString){
	 
		for(String c : Partie.plateauCase){
			if(c.equals(caseString))
				return true;
		}
		return false;
	}
	private static int translateColonne(char c) throws IdentifiantCaseException {
		switch(Character.toUpperCase(c))
		{
			case 'A' : return 0 ;
			case 'B' : return 1 ;
			case 'C' : return 2 ;
			case 'D' : return 3 ;
			case 'E' : return 4 ;
			case 'F' : return 5 ;
			case 'G' : return 6 ;
			case 'H' : return 7 ;
			default : throw new IdentifiantCaseException(Character.toString(c)) ;
		}
	}
	private static int translateLigne(char c)  throws IdentifiantCaseException {
		switch(Character.toUpperCase(c))
		{
			case '1' : return 7 ;
			case '2' : return 6 ;
			case '3' : return 5 ;
			case '4' : return 4 ;
			case '5' : return 3 ;
			case '6' : return 2 ;
			case '7' : return 1 ;
			case '8' : return 0 ;
			default : throw new IdentifiantCaseException(Character.toString(c)) ;
		}
	}
	/* -------------------------------------
	 *  Constructeur
	 ------------------------------------- */

	private Partie(int t, Plateau p) {
		this.setTurnNumber(t);
		this.setPlateau(p);
	}

	public Partie() {
		this( 0, null);
	}

	/* -------------------------------------
	 *  Accesseurs
	 ------------------------------------- */

	/*private int getTurnNumber() {
		return this.turnNumber;
	}*/

	private void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}
	
	private int getTurnNumber() {
		return this.turnNumber;
	}
	
	private void incrementTurnNumber() {
		this.setTurnNumber(this.getTurnNumber() + 1 );
	}
	
	private void setPlateau(Plateau p) {
		this.plateau = p;
	}

	private Plateau getPlateau() {
		return this.plateau;
	}
	
	/* -------------------------------------
	 *  Services classe privés
	 ------------------------------------- */


	/* -------------------------------------
	 *  Services instance publics
	 ------------------------------------- */
	/**
	 * Methode permettant de lancer une partie.
	 */
	public void lancerPartie() {
		try{
			this.menuLancementPartie();
			this.affichage();
			while(!this.getPlateau().matOrPatTest(this.colorToPlay()))
			{
				this.tourJeu();
				getPlateau().estEchec(colorToPlay());
			}
		}
		catch(IOException e) {
			System.err.println(e);
		}
	}
	
	private Case strToCase(String c) {
		try {
			int co = Partie.translateColonne(c.charAt(0));
			int li = Partie.translateLigne(c.charAt(1));
			if (getPlateau().getPiece(li,co) == null) {
				return Case.getCase(li, co);
			}
			else {
				return getPlateau().getPiece(li,co).getCase();
			}	
		}
		catch (IdentifiantCaseException e) {
			System.err.println(e);
			return null;
		}
	}
	
	private Case saisieCase() 
	{
		try {
			String saisie = scan.nextLine();
			while(! ( saisie.equals("save") || Partie.testCaseDansPlateau(saisie))){
				System.out.println("Votre case n'existe pas sur le plateau.\n Saisissez à nouveau la case : ");
				saisie = scan.nextLine();
			}
			if ( saisie.equals("save") ) {
				this.saveGame();
				return null;
			}
			else {
				Case c = this.strToCase(saisie);
				return c;
			}
			
		}
		catch(Exception e) {
			System.err.println("il y a une erreur dans le saisieCase :");
			System.err.println(e);
		} 
		return null;
	}
	
	private void affichage()
	{
		System.out.println(this.getPlateau().toString());
	}
	
	private char charColor() 
	{
		if (turnNumber%2 == 0) 
		{
			return 'B';
		}
		else 
		{
			return 'N';
		}
	}
	
	private boolean tourValide(Case c1, Case c2)
	{
		if (this.getPlateau().getPiece(c1) != null)
		{
			Piece p = this.getPlateau().getPiece(c1);
			if (p.getCouleur() == this.colorToPlay())
			{
				if (this.getPlateau().testDep(p, c2))
				{
					if (this.getPlateau().estEchec(p.getCouleur()))
					{
						System.out.println("vous êtes en échecs!");
						if (p instanceof Roi)
						{
							if (this.getPlateau().deplacementRoiPossible((Roi) p).contains(c2))
							{
								return true;
							}
						}
						else
						{
							if (this.getPlateau().peutBloquer(p, c2))
							{
								return !this.getPlateau().clouage(p);
							}
						}
					}
					else
					{
						if (p instanceof Roi)
						{
							return this.getPlateau().deplacementRoiPossible((Roi) p).contains(c2);
						}
						if (this.getPlateau().clouage(p))
						{
							return this.getPlateau().testBlocageClouage(p, c2);
						}
						else
						{
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	private void tourJeu() throws IOException
	{
		System.out.println("Tour du joueur : " + this.charColor() );
		System.out.println("Saisir la case de la pièce à jouer : \n " );
		Case c1 = this.saisieCase();
		System.out.println("Saisir la case où vous voulez la déplacer : \n " );
		Case c2 = this.saisieCase();
		if (this.tourValide(c1, c2))
		{
			this.getPlateau().deplacement(this.getPlateau().getPiece(c1), c2);
			this.affichage();
			this.incrementTurnNumber();
		}
		else
		{
			System.err.println("Coup invalide! Veuillez recommencer.");
		}
		
	}
	
	private void menuLancementPartie() 
	{
		System.out.println("(Vous pourez sauvegarder votre partie en tapant 'save' à la place d'une case de départ)");
		System.out.println("Que voulez-vous faire : \n\t 1 -> Nouvelle partie\n\t 2 -> Charger une partie");
		try
		{
			int choix = scan.nextInt();
			scan.nextLine();
			while (choix != 1 && choix != 2) {
				System.err.println("Choix incorrect. Tapez 1 ou 2");
				choix = scan.nextInt();
				scan.nextLine();
			}
			// Initialisation du plateau
			if (choix == 1) {
				this.newGame();
			}
			else {
				this.loadGame();
			}
		} 
		catch (ClassNotFoundException | IOException e) 
		{
			e.printStackTrace();
		} 
	}
	
	
	private void newGame () throws IOException
	{
		this.plateau = new Plateau();
	}
	
	
	private void loadGame() throws IOException, ClassNotFoundException
	{
			System.out.println("Nom de la sauvegarde :");
			String fileName = scan.nextLine();
			FileInputStream fin = new FileInputStream("./saves/"+ fileName + ".ser");
			ObjectInputStream ois = new ObjectInputStream(fin);
			Plateau p =  (Plateau) ois.readObject();
			ois.close();
			this.setPlateau(p);
	}
	
	private void saveGame()throws IOException
	{
		
			System.out.println("Nom de la sauvegarde : ");
			String fileName= scan.nextLine();
			File file = new File("./saves/" + fileName + ".ser");
			file.createNewFile();
			try
			{
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.getPlateau());
			oos.close();
			System.exit(0);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
	}
	
	
	private boolean colorToPlay() 
	{
		if (turnNumber%2 == 0)
			return true;
		else
			return false;
	}
}