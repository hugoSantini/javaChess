package projet_echecs;

import java.util.ArrayList;

public class Cavalier extends Piece {
	
	private static final long serialVersionUID = 1L;

	/* -------------------------------------
	 *  Constructeur
	 ------------------------------------- */
	/**
	 * Methode constructeur de la class Cavalier.
	 * @param couleur Boolean qui traduit la couleur de la piece (True = Blanc, False = Noir).
	 */
	public Cavalier(boolean couleur) 
	{
		super(couleur);
	}
	
	/* -------------------------------------
	 *  Méthode de classe
	 ------------------------------------- */
	/**
	 * Dis si le coup est conforme au déplacement de la pièce.
	 * @param c La case cible du Cavalier courant.
	 * @return Un boolean qui traduit la possibilité du coup.
	 */
	public boolean deplacementOk(Case c)
	{
		if ((c.getLigne() == this.getCase().getLigne() + 2 || c.getLigne() == this.getCase().getLigne() -2) && (c.getColonne() == this.getCase().getColonne() + 1 || c.getColonne() == this.getCase().getColonne() - 1 ))
		{
			return true;
		}
		else if ((c.getLigne() == this.getCase().getLigne() + 1 || c.getLigne() == this.getCase().getLigne() -1) && (c.getColonne() == this.getCase().getColonne() + 2 || c.getColonne() == this.getCase().getColonne() - 2))
		{
			return true;
		}
	    return false;
	}
	/**
	 * Dis si le coup est conforme au déplacement de la pièce et prend en compte les colisions avec les autres pièces du plateau.
	 * @param c La case cible du Cavalier courant.
	 * @return Un boolean qui traduit la possibilité du coup avec prise en compte des colisions.
	 */
	public boolean deplacementPossible(Case c)
	{
		return this.deplacementOk(c);
	}
	
	/**
	 * Fait une liste des cases d'arrivés possibles du Cavalier.
	 * @return Renvoie la liste des cases repondant à déplacement possible.
	 */
	public ArrayList<Case> CasesPossible()
	{
		ArrayList<Case> CaseP = new ArrayList<Case>();
		
		for (int i = 0; i < 8; i++)
		{
			for (int j = 0; j < 8; j ++)
			{
				if (this.deplacementPossible(Case.getCase(i, j)))
				{
					CaseP.add(Case.getCase(i, j));
				}
			}
		}
		
		if (CaseP.isEmpty())
		{
			return null;
		}
		return CaseP;
	}
}
