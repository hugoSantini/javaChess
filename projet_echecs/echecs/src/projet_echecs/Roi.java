package projet_echecs;

import java.util.ArrayList;

public class Roi extends Piece
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* -------------------------------------
	 *  Variable d'instance
	 ------------------------------------- */
	
	private boolean estEchec;
	
	/* -------------------------------------
	 *  Constructeur
	 ------------------------------------- */
	/**
	 * Methode constructeur de la class Roi.
	 * @param couleur Boolean qui traduit la couleur de la piece (True = Blanc, False = Noir).
	 */
	public Roi(boolean couleur)
	{
		super(couleur);
	}
	
	/* -------------------------------------
	 *  Accesseurs
	 ------------------------------------- */
	/**
	 * Methode qui renvoie si un roi est en echec ou pas.
	 * @return Un boolean traduisant l'état d'echec.
	 */
	public boolean getEstEchec()
	{
		return this.estEchec;
	}
	/**
	 * Methode permet de set l'état d'echec d'un Roi.
	 * @param Un boolean qui traduira l'état d'echec d'un Roi.
	 */
	public void setEstEchec(boolean b)
	{
		this.estEchec = b;
	}
	
	/* -------------------------------------
	 *  Méthode de classe
	 ------------------------------------- */
	/**
	 * Dis si le coup est conforme au déplacement de la pièce.
	 * @param c La case cible du Roi courant.
	 * @return Un boolean qui traduit la possibilité du coup.
	 */
	public boolean deplacementOk(Case c)
	{
    	if(     (c.getLigne() == this.getCase().getLigne() || c.getLigne() == this.getCase().getLigne() - 1 || c.getLigne() == this.getCase().getLigne() + 1)
    			&&
    			(-1 <= c.getColonne() - this.getCase().getColonne() && c.getColonne() - this.getCase().getColonne() <= 1)
    			&&
    			(c != this.getCase()))
    	{
    		return true;
    	}
    	return false;
    }
	/**
	 * Dis si le coup est conforme au déplacement de la pièce et prend en compte les colisions avec les autres pièces du plateau.
	 * @param c La case cible du Roi courant.
	 * @return Un boolean qui traduit la possibilité du coup avec prise en compte des colisions.
	 */
	public boolean deplacementPossible(Case c)
	{
		return this.deplacementOk(c);
	}
	
	/**
	 * Fait une liste des cases d'arrivés possibles du Roi.
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
