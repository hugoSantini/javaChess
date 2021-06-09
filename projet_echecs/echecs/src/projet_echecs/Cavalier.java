package projet_echecs;

import java.util.ArrayList;

public class Cavalier extends Piece {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cavalier(boolean couleur) 
	{
		super(couleur, "C");
	}
	
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
	
	public boolean deplacementPossible(Case c)
	{
		return this.deplacementOk(c);
	}
	
	//Renvoie la liste des cases repondant a deplacement possible
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
