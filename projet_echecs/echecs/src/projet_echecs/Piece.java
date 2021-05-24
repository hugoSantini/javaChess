package projet_echecs;

public class Piece {
	
	private Case c;
	private char couleur;
	private String Nom;
	protected int[][] Co;
	
	
	public Piece (char couleur, String Nom)
	{
		this.couleur = couleur;
		this.Nom = Nom;
	}
	
	public int[] getCo(int i)
	{
		return this.Co[i];
	}

	public Case getCase() 
	{
		return this.c;
	}

	public void setCase(Case c) 
	{
		this.c = c;
	}

	public char getCouleur()
	{
		return this.couleur;
	}

	public void setCouleur(char couleur) 
	{
		this.couleur = couleur;
	}
	
	public boolean deplacementOk(Case caseA)
	{
		return false;
	}
	
	public boolean deplacer(Case caseA)
	{
		return true;
	}
	
	
	public String toString() 
	{
		return this.Nom + this.couleur;
	}
}
