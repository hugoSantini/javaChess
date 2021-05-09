package javaChess;

public class Piece {
	protected Case position;
	protected char couleur;

	public Case getPosition() {
		return this.position;
	}

	public void setPosition(Case position) {
		this.position = position;
	}

	public char getCouleur() {
		return this.couleur;
	}

	public void setCouleur(char couleur) {
		this.couleur = couleur;
	}
}
