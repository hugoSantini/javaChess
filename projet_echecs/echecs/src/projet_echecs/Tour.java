package projet_echecs;

import java.util.ArrayList;

public class Tour extends Piece {

	public Tour(char couleur) {
		super(couleur, "T");
	}
	
	
	public boolean deplacementOk(Case c)
	{
    	if(c.getLigne() == this.getCase().getLigne() || c.getColonne() == this.getCase().getColonne())
    	{
    		return true;
    	}
    	return false;
    }
	
	//Renvoie un boolean en fonction de si le deplacement de la piece courante vers la case c est possible (en tenant compte des autres pieces et en fesant le test deplacement ok)
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