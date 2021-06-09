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
	 * Getter de la case de la pièce.
	 * @return La Case de la pièce courante.
	 */
	
	public Case getCase() 
	{
		return this.c;
	}
	
	/**
	 * Setter de la case de la pièce.
	 * @param c Case a affecter a la pièce.
	 */
	
	public void setCase(Case c) 
	{
		this.c = c;
	}
	
	/**
	 * Getter de premierCoup.
	 * @return Un boolean traduisant si la pièce à déjà joué un coup.
	 */
	
	public boolean getPremierCoup()
	{
		return this.premierCoup;
	}
	
	/**
	 * Setter de premierCoup.
	 * @param b boolean de premierCoup a affecter a la pièce.
	 */
	
	public void setPremierCoup(boolean b)
	{
		this.premierCoup = b;
	}
	
	/**
	 * Getter de la couleur de la pièce.
	 * @return Un boolean traduisant la couleur de la pièce.
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
	 *  Méthode de classe abstraite
	 ------------------------------------- */
	
	public abstract boolean deplacementOk(Case caseA);
	
	public abstract boolean deplacementPossible(Case c);
	
	public abstract ArrayList<Case> CasesPossible();
	
	/* -------------------------------------
	 *  Méthode de classe
	 ------------------------------------- */
	
	/**
	 * toString de la class Pièce.
	 * @return Un String comprenant la couleur et le type de la pièce.
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
