package javaChess;

public class Case {
	private char ligne;
	private int colonne;
	
	public Case(char ligne,int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}

	public char getLigne() {
		return ligne;
	}

	public void setLigne(char ligne) {
		this.ligne = ligne;
	}

	public int getColonne() {
		return colonne;
	}

	public void setColonne(int colonne) {
		this.colonne = colonne;
	}
	
	
	public static void main(String[] args) {
		
		Case a1 = new Case('a',1);
		
	}
}
