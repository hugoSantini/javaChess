package javaChess;
import java.util.Scanner;

public class Start {
	
	public Start() {
		 try (Scanner scan = new Scanner(System.in)) {
			System.out.println("Que voulez-vous faire : newGame, load, listSave");
			String actionUser = scan.nextLine();
			while (actionUser != "newGame" || actionUser != "load" || actionUser != "listSave") {
				actionUser = scan.nextLine();
			}
			if (actionUser == "newGame") {
				String nameGame = scan.nextLine();
				Partie P = new Partie();
				
			}
			else if (actionUser == "load") {
				String nameSave = scan.nextLine();
		}
	}
}
}
