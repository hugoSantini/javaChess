package javaChess;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Partie {
	static int turnNumber;
	private int[] score; // 0 = Black Won, 1 = White Won
	private String name;
	private String[][] listMooves;// Forme : [[a1,a2],[b6,b3],[a4,b4]...]
	private Plateau plateau;

	public Partie(String name){
		this.name = name;
		turnNumber = 0;
		score = 2;
		Scanner scan = new Scanner(System.in);
		plateau = new Plateau()
		
	}
	
	public int getTurnNumber() {
		return Partie.turnNumber;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void turnNumberAdd(int turnNumber) {
		Partie.turnNumber += 1;
	}
	
	public void blackWin() {
		this.score[1] += 1;
	}
	
	public void whiteWin() {
		this.score[0] += 1;
	}
	public String turn() {
		Partie.turnNumber += 1;
		Plateau.afficher();
		if (turnNumber % 2 = 0) {
			System.out.println("Trait aux blancs");
		}	
		else {
			System.out.println("Trait aux noirs");
		}
	
	return null;
	}
	
	public void resetScore() {
		
	}

	public void endGame() {
		
	}
	
	public void initPartie() {
		
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private void exportMoove() {
	    try {
	    	Scanner scan = new Scanner(System.in);
	    	System.out.println("Save Name :");
	    	String nameSave = "../save"+ scan.nextLine();
	        File save = new File(nameSave);
	        while (save.createNewFile() == false) {
	        	System.out.println("Name already taken, please choose another one.");
	        } 
	        	System.out.println("File created !");
	        
	      }
	    catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	    try {
	        FileWriter myWriter = new FileWriter("../filename.txt");
	        myWriter.close();
	        System.out.println("Save created !");
	      }
	    catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	}
}

		