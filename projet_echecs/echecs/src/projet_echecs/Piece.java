package projet_echecs;

import java.util.ArrayList;

public class Piece {
	
	private Case c;
	private char couleur;
	private String Nom;
	private boolean premierCoup;
	
	
	public Piece (char couleur, String Nom)
	{
		this.couleur = couleur;
		this.Nom = Nom;
		this.premierCoup = true;
	}

	public Case getCase() 
	{
		return this.c;
	}

	public void setCase(Case c) 
	{
		this.c = c;
	}
	
	public boolean getPremierCoup()
	{
		return this.premierCoup;
	}
	
	public void setPremierCoup(boolean b)
	{
		this.premierCoup = b;
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
	
	public boolean deplacementPossible(Case c)
	{
		return false;
	}
	
	public ArrayList<Case> CasesPossible()
	{
		ArrayList<Case> CaseP = new ArrayList<Case>();
		return CaseP;
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
