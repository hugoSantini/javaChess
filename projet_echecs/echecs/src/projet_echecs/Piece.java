package projet_echecs;

import java.util.ArrayList;

public class Piece {
	
	private Case c;
	private char couleur;
	private String Nom;
	private boolean peutPrendre;
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
	
	public void setPeutPrendre()
	{
		this.peutPrendre = false;
		for (Case c : this.CasesPossible())
		{
			if (c.getPresence())
			{
				this.peutPrendre = true;
			}
		}
	}
	
	public void setPeutPrendre(boolean b)
	{
		this.peutPrendre = b;
	}
	
	public boolean getPeutPrendre()
	{
		return this.peutPrendre;
	}
	
	public boolean deplacementOk(Case caseA)
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
