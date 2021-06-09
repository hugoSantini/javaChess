package projet_echecs;

import java.util.ArrayList;

public class Roi extends Piece
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean estEchec;

	public Roi(boolean couleur)
	{
		super(couleur, "R");
	}
	
	public boolean getEstEchec()
	{
		return this.estEchec;
	}
	
	public void setEstEchec(boolean b)
	{
		this.estEchec = b;
	}
	
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
