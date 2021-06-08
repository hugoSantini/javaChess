
//*


/**
 * Classe gérant la partie
 * 
 * @author Hugo Santini , Lucas Mennessier
 * @version 1.0
 */

package projet_echecs;

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
	public void lancerPartie() {
		try{
			this.menuLancementPartie();
			while(!this.getPlateau().matOrPatTest()){
				System.out.println(getPlateau().testMat());
				System.out.println(getPlateau().deplacementRoiPossible(getPlateau().getRoi(true)).toString());
				System.out.println(plateau.estEchec(colorToPlay()));
				System.out.println(getPlateau().getRoi(colorToPlay()).getEstEchec());
				this.tourDeJeu();
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
	
	private Case saisieCase() {
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
			System.err.println("Ya une erreur dans le saisieCase :");
			System.err.println(e);
		} 
		return null;
	}
	private void affichage(){
		System.out.println(this.getPlateau().toString());
	}
	private char charColor() {
		if (turnNumber%2 == 0) {
			return 'B';
		}
		else {
			return 'N';
		}
	}
	
	private void tourDeJeu() throws IOException{
		System.out.println("Tour du joueur : " + this.charColor() );
		System.out.println("Saisir la case de la pièce à jouer : \n " );
		if (this.getPlateau().estEchec(colorToPlay())) {
			this.affichage();
			System.out.println("Vous êtes en echec !" );
			Case c1 = this.saisieCase();
			while(getPlateau().getPiece(c1) == null) {
				System.err.println("Il n'y a pas de pièce sur la case saisi.");
				System.out.println("Saisir la case de la pièce à jouer : ");
				c1 = this.saisieCase();
				if (getPlateau().getPiece(c1) != null) {
					if ((getPlateau().getPiece(c1).getCouleur() != this.colorToPlay())) {
						System.err.println("Il n'y a pas de pièce sur la case saisi ou celle-ci est de la mauvaise couleur.");
						System.out.println("Saisir la case de la pièce à jouer : ");
						c1 = this.saisieCase();
					}
					else if ( ! (getPlateau().deplacementPiecePossible(getPlateau().getPiece(c1)).size() > 0)) {
						System.err.println("La piece choisi n'a pas de déplacement possible.");
						System.out.println("Saisir la case de la pièce à jouer : ");
						c1 = this.saisieCase();
					}
					else if (getPlateau().clouage(getPlateau().getPiece(c1))) {
						System.err.println("La piece choisi est cloué !");
						System.out.println("Saisir la case de la pièce à jouer : ");
						c1 = this.saisieCase();
					}
					else {
							if (getPlateau().getPiece(c1) instanceof Roi) {
								if (! getPlateau().deplacementRoiPossible((Roi) getPlateau().getPiece(c1)).isEmpty()) {
									System.out.println("Ou voulez-vous déplacer votre roi :");
									System.out.println("Cases possible : " + casePossibleTraduitRoi(getPlateau().getPiece(c1)));
									Case c2 = this.saisieCase();
									while (getPlateau().deplacementRoiPossible((Roi) getPlateau().getPiece(c1)).contains(c2)){
										System.err.println("La case de destination est invalide :");
										System.out.println("Ou voulez-vous déplacer votre roi : "+ casePossibleTraduitRoi(getPlateau().getPiece(c1)));
										c2 = this.saisieCase();
									}
									getPlateau().deplacement(getPlateau().getPiece(c1), c2);
								}
							
						}
							else if(! getPlateau().bloqueEchec(getPlateau().getPiece(c1)).isEmpty()) {
								System.out.println("La piece selectioné peut bloquer l'echec en :" + casePossibleTraduitBloqueEchec(getPlateau().getPiece(c1)));
								Case c2 = this.saisieCase();
								while (getPlateau().bloqueEchec(getPlateau().getPiece(c1)).contains(c2)){
									System.err.println("La case de destination est invalide :");
									System.out.println("Votre coup ne bloque pas l'echec : " + casePossibleTraduitBloqueEchec(getPlateau().getPiece(c1)));
									c2 = this.saisieCase();
							}
								getPlateau().deplacement(getPlateau().getPiece(c1), c2);
						}

					}
				}
			}
		}
		else {
			this.affichage();
			Case c1 = this.saisieCase();
			while(getPlateau().getPiece(c1) == null) {
				System.err.println("Il n'y a pas de pièce sur la case saisi.");
				System.out.println("Saisir la case de la pièce à jouer : ");
				c1 = this.saisieCase();
				if (getPlateau().getPiece(c1) != null) {
					if ((getPlateau().getPiece(c1).getCouleur() != this.colorToPlay())) {
						System.err.println("Il n'y a pas de pièce sur la case saisi ou celle-ci est de la mauvaise couleur.");
						System.out.println("Saisir la case de la pièce à jouer : ");
						c1 = this.saisieCase();
					}
					else if ( ! (getPlateau().deplacementPiecePossible(getPlateau().getPiece(c1)).size() > 0)) {
						System.err.println("La piece choisi n'a pas de déplacement possible.");
						System.out.println("Saisir la case de la pièce à jouer : ");
						c1 = this.saisieCase();
					}
					else if (getPlateau().clouage(getPlateau().getPiece(c1))) {
						System.err.println("La piece choisi est cloué !");
						System.out.println("Saisir la case de la pièce à jouer : ");
						c1 = this.saisieCase();
					}
				}
			}
			System.out.println("Saisir la case destination : " );
			System.out.println("Cases possible : " + casePossibleTraduit(getPlateau().getPiece(c1)));
			Case c2 = saisieCase();
			while(!this.getPlateau().testDep(getPlateau().getPiece(c1),c2)){
				System.err.println("La case choisi n'est pas dans les case possible : " + getPlateau().deplacementPiecePossible(getPlateau().getPiece(c1)).toString());
				c2 = saisieCase();
			}
			getPlateau().deplacement(getPlateau().getPiece(c1), c2);
			this.incrementTurnNumber();
		}
	}
	
	
	private void menuLancementPartie() {
		System.out.println("(Vous pourez sauvegarder votre partie en tapant 'save' à la place d'une case de départ)");
		System.out.println("Que voulez-vous faire : \n\t 1 -> Nouvelle partie\n\t 2 -> Charger une partie");
		try {
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
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} 
	}
	public void newGame () throws IOException {
		this.plateau = new Plateau();
	}
	private void loadGame() throws IOException, ClassNotFoundException{
			System.out.println("Nom de la sauvegarde :");
			String fileName = scan.nextLine();
			FileInputStream fin = new FileInputStream("./saves/"+ fileName + ".txt");
			ObjectInputStream ois = new ObjectInputStream(fin);
			Plateau p =  (Plateau) ois.readObject();
			ois.close();
			this.setPlateau(p);
	}
	
	private void saveGame()throws IOException{
			System.out.println("Nom de la sauvegarde :");
			String fileName= scan.nextLine();
			FileOutputStream fos = new FileOutputStream("./saves/"+ fileName + ".txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this.getPlateau());
			oos.close();
			System.exit(0);
		}
	private boolean colorToPlay() {
		if (turnNumber%2 == 0)
			return true;
		else
			return false;
	}
	public String casePossibleTraduitRoi(Piece p) {
		ArrayList<Case> array = this.plateau.deplacementRoiPossible((Roi) p);
		String s = "[";
		for (int i = 0; i < array.size(); i++) {
	          	s += String.valueOf(array.get(i).getNomColonne()) + String.valueOf(array.get(i).getNomLigne()) + ",";
	      }
		
		s = s.substring(0,s.length()-1);
		s +="]";
		return s;
		}

	public String casePossibleTraduitBloqueEchec(Piece p) {
		ArrayList<Case> array = this.plateau.bloqueEchec(p);
		String s = "[";
		for (int i = 0; i < array.size(); i++) {
	          	s += String.valueOf(array.get(i).getNomColonne()) + String.valueOf(array.get(i).getNomLigne()) + ",";
	      }
		
		s = s.substring(0,s.length()-1);
		s +="]";
		return s;
		}
	public String casePossibleTraduit(Piece p) {
		ArrayList<Case> array = this.plateau.deplacementPiecePossible(p);
		String s = "[";
		for (int i = 0; i < array.size(); i++) {
	          	s += String.valueOf(array.get(i).getNomColonne()) + String.valueOf(array.get(i).getNomLigne()) + ",";
	      }
		
		s = s.substring(0,s.length()-1);
		s +="]";
		return s;
		}
}
