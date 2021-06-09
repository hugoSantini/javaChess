package projet_echecs;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Piece implements Serializable{
	
	/* -------------------------------------
	 *  Variable d'instance
	 ------------------------------------- */
	
	private static final long serialVersionUID = 1L;
	private Case c;
	private boolean couleur;
	private boolean premierCoup;
	
	/* -------------------------------------
	 *  Constructeur
	 ------------------------------------- */
	
	/**
	 * Methode constructeur de la class piece
	 * @param couleur Boolean qui traduit la couleur de la piece (True = Blanc, False = Noir)
	 */
	
	public Piece (boolean couleur)
	{
		this.couleur = couleur;
		this.premierCoup = true;
	}

	/* -------------------------------------
	 *  Accesseurs
	 ------------------------------------- */
	
	/**
	 * Getter de la case de la pi�ce.
	 * @return La Case de la pi�ce courante.
	 */
	
	public Case getCase() 
	{
		return this.c;
	}
	
	/**
	 * Setter de la case de la pi�ce.
	 * @param c Case a affecter a la pi�ce.
	 */
	
	public void setCase(Case c) 
	{
		this.c = c;
	}
	
	/**
	 * Getter de premierCoup.
	 * @return Un boolean traduisant si la pi�ce � d�j� jou� un coup.
	 */
	
	public boolean getPremierCoup()
	{
		return this.premierCoup;
	}
	
	/**
	 * Setter de premierCoup.
	 * @param b boolean de premierCoup a affecter a la pi�ce.
	 */
	
	public void setPremierCoup(boolean b)
	{
		this.premierCoup = b;
	}
	
	/**
	 * Getter de la couleur de la pi�ce.
	 * @return Un boolean traduisant la couleur de la pi�ce.
	 */
	
	public boolean getCouleur()
	{
		return this.couleur;
	}

	private void setCouleur(boolean couleur) 
	{
		this.couleur = couleur;
	}
	
	/* -------------------------------------
	 *  M�thode de classe abstraite
	 ------------------------------------- */
	
	public abstract boolean deplacementOk(Case caseA);
	
	public abstract boolean deplacementPossible(Case c);
	
	public abstract ArrayList<Case> CasesPossible();
	
	/* -------------------------------------
	 *  M�thode de classe
	 ------------------------------------- */
	
	/**
	 * toString de la class Pi�ce.
	 * @return Un String comprenant la couleur et le type de la pi�ce.
	 */
	
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
