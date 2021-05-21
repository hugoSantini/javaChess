package projet_echecs;

public class Piece {
	
	private Case position;
	private char couleur;
	private String Nom;
	
	
	public Piece (Case pos, char couleur, String Nom){
		this.position = pos;
		this.couleur = couleur;
		this.Nom = Nom;
	}

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
	
	public String toString() {
		return this.Nom;
	}
}
