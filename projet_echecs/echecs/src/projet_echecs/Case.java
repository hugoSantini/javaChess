package projet_echecs;

import java.io.Serializable;

public class Case implements Serializable{
	

	/* -------------------------------------
	 *  Variable d'instance
	 ------------------------------------- */
	
	private static final long serialVersionUID = 1L;
	private int Ligne;
	private int Colonne;
	private boolean presence;
	
	/* -------------------------------------
	 *  Constante de classe
	 ------------------------------------- */
	
	private static Case[][] Cases = new Case[8][8];

	/* -------------------------------------
	 *  Constructeur
	 ------------------------------------- */
	/**
	 * Methode constructeur vide de la class Case.
	 */
	public Case()
	{
	}
	
	/**
	 * Methode constructeur de la class Case.
	 * @param L Int désignant la Ligne.
	 * @param C Int désignant la colonne.
	 */
	
	public Case(int L, int C)
	{
		this.Ligne = L;
		this.Colonne = C;
		this.presence = false;
		
		Cases[L][C] = this;
	}
	
	/**
	 * Methode constructeur par copie de la class Case.
	 * @param c Case a copier.
	 */
	
	public Case(Case c)
	{
		this.Ligne = c.getLigne();
		this.Colonne = c.getColonne();
	}
	
	/* -------------------------------------
	 *  Accesseurs
	 ------------------------------------- */
	
	/**
	 * Getter d'une case dans 'Cases'.
	 * @param L Ligne de la case.
	 * @param C Colonne de la case.
	 * @return La case voulue.
	 */
	
	public static Case getCase(int L, int C)
	{
		return Cases[L][C];
	}
	/**
	 * Getter de la ligne d'une case.
	 * @return La ligne de la case courante.
	 */

	public int getLigne() {
		return this.Ligne;
	}

	private void setLigne(int ligne) {
		this.Ligne = ligne;
	}
	
	/**
	 * Getter de la colonne d'une case.
	 * @return La colonne de la case courante.
	 */
	
	public int getColonne() {
		return this.Colonne;
	}	

	private void setColonne(int colonne) {
		this.Colonne = colonne;
	}
	
	/**
	 * Getter de la présence d'une pièce sur une case.
	 * @return Un boolean traduisant la présence d'une sur la case courante.
	 */
	
	public boolean getPresence()
	{
		return this.presence;
	}
	
	/**
	 * Setter de la présence d'une pièce sur une case.
	 * @param b Un boolean traduisant la présence d'une sur la case courante.
	 */
	
	public void setPresence(boolean b)
	{
		this.presence = b;
	}
	
	/* -------------------------------------
	 *  Méthode de classe
	 ------------------------------------- */
	
	private char getNomColonne()
	{
		switch(this.Colonne)
		{
			case 0 : return 'A' ;
			case 1 : return 'B' ;
			case 2 : return 'C' ;
			case 3 : return 'D' ;
			case 4 : return 'E' ;
			case 5 : return 'F' ;
			case 6 : return 'G' ;
			case 7 : return 'H' ;
			default : return 'O' ;
		}
	}
	private int getNomLigne() {
		switch(this.Ligne)
		{
			case 0 : return 8 ;
			case 1 : return 7 ;
			case 2 : return 6 ;
			case 3 : return 5 ;
			case 4 : return 4 ;
			case 5 : return 3 ;
			case 6 : return 2 ;
			case 7 : return 1 ;
			default : return 'O' ;
		}
	}
	
	/**
	 * toString de la class Case.
	 * @param Un String de la ligne et colonne de la case courante.
	 */

	public String toString()
	{
		return String.valueOf(this.Ligne) + " " + String.valueOf(this.Colonne);
	}
}
