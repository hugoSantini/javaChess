package projet_echecs;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Piece implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Case c;
	private boolean couleur;
	private boolean premierCoup;
	
	
	public Piece (boolean couleur, String Nom)
	{
		this.couleur = couleur;
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

	public boolean getCouleur()
	{
		return this.couleur;
	}

	public void setCouleur(boolean couleur) 
	{
		this.couleur = couleur;
	}
	
	public abstract boolean deplacementOk(Case caseA);

	
	public abstract boolean deplacementPossible(Case c);
	
	public abstract ArrayList<Case> CasesPossible();
	
	public String toString()
	{
		String c = this.getClass().getSimpleName();
		c = c.substring(0,1);
		String cou;
		if (this.getCouleur()) {
			cou = "B";
		}
		else {
			cou = "N";
			}

		return c + cou;
	}

}
