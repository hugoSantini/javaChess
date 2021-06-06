package projet_echecs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Partie {
	private int turnNumber;
	private Plateau plateau;
	private static String[] plateauCase = {
	                              "a8","b8","c8","d8","e8","f8","g8","h8",
	                              "a7","b7","c7","d7","e7","f7","g7","h7",
	                              "a6","b6","c6","d6","e6","f6","g6","h6",
	                              "a5","b5","c5","d5","e5","f5","g5","h5",
	                              "a4","b4","c4","d4","e4","f4","g4","h4",
	                              "a3","b3","c3","d3","e3","f3","g3","h3",
	                              "a2","b2","c2","d2","e2","f2","g2","h2",
	                              "a1","b1","c1","d1","e1","f1","g1","h1"
	};

	public Partie() {
		turnNumber = 0;
		startGame();
	}

	public Partie(Plateau p) {
		this.turnNumber = 0;
		this.plateau = p;
		startGame();
	}

	public int getTurnNumber() {
		return this.turnNumber;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber = turnNumber;
	}

	public void menu() {
		System.out.println("Que voulez-vous faire : load, newGame");
		try (Scanner scan = new Scanner(System.in)) {
			String choix = scan.nextLine();
			while (choix != "load" || choix != "newGame") {
				choix = scan.nextLine();
			}
			if (choix == "load") {
				this.plateau = load();
			}
			else {
				startGame();
			}
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

	}

	public static void save(Plateau p)throws IOException{
		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("Nom de la sauvegarde :");
			String fileName= scan.nextLine();
			FileOutputStream fos = new FileOutputStream("./saves/"+ fileName + ".txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(p);
			oos.close();
		}
	}

	public static Plateau load() throws IOException, ClassNotFoundException{
		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("Nom de la sauvegarde :");
			String fileName = scan.nextLine();
			FileInputStream fin = new FileInputStream("./saves/"+ fileName + ".txt");
			ObjectInputStream ois = new ObjectInputStream(fin);
			Plateau p =  (Plateau) ois.readObject();
			ois.close();
			return p;
		}


	}
	public void turn() {
		if (turnNumber%2 == 1) {
			traitAuBlanc();
		}
		else traitAuNoir();
	}

	public void resetScore () {

	}

	public void startGame () {
		while (this.plateau.mateTest() == false) {

		}
	}

	public void listSave (){

	}

	public void endGame () {

	}
	public void traitAuBlanc() {
		try (Scanner scan = new Scanner(System.in)) {
			String c = scan.nextLine();

			this.plateau.plateau[0][0].getCouleur(stringToCase(c));
		}
		
	}
	public void traitAuNoir() {

	}
	public Case stringToCase(String str) {
		if (testCaseDansPlateau(str)) {
			System.out.println("Votre case n'existe pas sur le plateau.");
			return null;
		}
		else {
			return null;
		}

	}
	public String translateCollone(String s) {
		return null;
	}
	public void continueGame() {

	}
	public boolean testCaseDansPlateau(String caseString){
		for(String s: plateauCase){
			if(s.equals(caseString))
				return true;
		}
		return false;
	}
}
