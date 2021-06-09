package projet_echecs;

import java.util.ArrayList;

public class Tour extends Piece {

	private static final long serialVersionUID = 1L;

	/* -------------------------------------
	 *  Constructeur
	 ------------------------------------- */
	/**
	 * Methode constructeur de la class Tour.
	 * @param couleur Boolean qui traduit la couleur de la piece (True = Blanc, False = Noir).
	 */
	public Tour(boolean couleur) {
		super(couleur);
	}
	
	/* -------------------------------------
	 *  Méthode de classe
	 ------------------------------------- */
	/**
	 * Dis si le coup est conforme au déplacement de la pièce.
	 * @param c La case cible de la Tour courante.
	 * @return Un boolean qui traduit la possibilité du coup.
	 */
	public boolean deplacementOk(Case c)
	{
    	if(c.getLigne() == this.getCase().getLigne() || c.getColonne() == this.getCase().getColonne())
    	{
    		return true;
    	}
    	return false;
    }
	/**
	 * Dis si le coup est conforme au déplacement de la pièce et prend en compte les colisions avec les autres pièces du plateau.
	 * @param c La case cible de la Tour courant.
	 * @return Un boolean qui traduit la possibilité du coup avec prise en compte des colisions.
	 */
	public boolean deplacementPossible(Case c)
	{
		if(this.deplacementOk(c))
		{
		    if(this.getCase().getLigne() != c.getLigne() && this.getCase().getColonne() == c.getColonne())
		    {	    	
		    	if(this.getCase().getLigne() > c.getLigne())
		    	{ // bas vers haut
		    		for(int i = this.getCase().getLigne() - 1 ; i > c.getLigne() ; i--)
		    		{
		    			if(Case.getCase(i,this.getCase().getColonne()).getPresence()){
		    				return false;
		    			}
		    		}
		    		return true;
		    	}
		    	if(this.getCase().getLigne() < c.getLigne())
		    	{ // haut vers bas
		    		for(int i = this.getCase().getLigne() + 1 ; i < c.getLigne() ; i++)
		    		{
		    			if(Case.getCase(i,this.getCase().getColonne()).getPresence()){
		    				return false;
		    			}
		    		}
		    		return true;
		    	}
		    }
		    if(this.getCase().getLigne() == c.getLigne() && this.getCase().getColonne() != c.getColonne())
		    {
		    	if(this.getCase().getColonne() > c.getColonne())
		    	{ // droite vers gauche
		    		for(int i = this.getCase().getColonne() - 1 ; i > c.getColonne() ; i--)
		    		{
		    			if(Case.getCase(this.getCase().getLigne(),i).getPresence())
		    			{
		    				return false;
		    			}
		    		}
		    		return true;
		    	}
		    	if(this.getCase().getColonne() < c.getColonne())
		    	{ // gauche vers droite
		    		for(int i = this.getCase().getColonne() + 1 ; i < c.getColonne() ; i++)
		    		{
		    			if(Case.getCase(this.getCase().getLigne(),i).getPresence())
		    			{
		    				return false;
		    			}
		    		}
		    		return true;
		    	}
		    }
		    return false;
	    }
		return false;
	}
	
	/**
	 * Fait une liste des cases d'arrivés possibles de la Tour.
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