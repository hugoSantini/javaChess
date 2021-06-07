package projet_echecs;

import java.util.ArrayList;

public class Fous extends Piece{
	

	public Fous(boolean couleur) {
		super(couleur, "F");
	}
		
	public boolean deplacementOk(Case c)
	{
	    for (int i = 0; i < 8; i++)
	    {
	    	if (this.getCase().getColonne() == c.getColonne() + i && this.getCase().getLigne() == c.getLigne() + i)
	    	{
	    		return true;
	    	}
	    	else if (this.getCase().getColonne() == c.getColonne() - i && this.getCase().getLigne() == c.getLigne() - i)
	    	{
	    		return true;
	    	}
	    	else if (this.getCase().getColonne() == c.getColonne() + i && this.getCase().getLigne() == c.getLigne() - i)
	    	{
	    		return true;
	    	}
	    	else if (this.getCase().getColonne() == c.getColonne() - i && this.getCase().getLigne() == c.getLigne() + i)
	    	{
	    		return true;
	    	}		
	    }
	    return false;
	}
	
	public boolean deplacementPossible(Case c)
	{
		if(this.deplacementOk(c))
		{
		    if(this.getCase().getLigne() > c.getLigne())  // vers le haut
		    {	    
		    	if(this.getCase().getColonne() > c.getColonne()) // vers la gauche
		    	{
		    		for(int i = 1 ; i < this.getCase().getColonne() - c.getColonne() ; i++)
		    		{
		    			if(Case.getCase(this.getCase().getLigne() - i, this.getCase().getColonne() - i).getPresence())
		    			{
		    				return false;
		    			}
		    		}
		    		return true;
		    	}
		    	if(this.getCase().getColonne() < c.getColonne()) // vers la droite
		    	{ // gauche vers droite
		    		for(int i = 1 ; i < c.getColonne() - this.getCase().getColonne() ; i++)
		    		{
		    			if(Case.getCase(this.getCase().getLigne() - i, this.getCase().getColonne() + i).getPresence())
		    			{
		    				return false;
		    			}
		    		}
		    		return true;
		    	}
		    }
		    if(this.getCase().getLigne() < c.getLigne()) // vers le bas
		    {
		    	if(this.getCase().getColonne() > c.getColonne()) // vers le gauche
		    	{ // droite vers gauche
		    		for(int i = 1 ; i < this.getCase().getColonne() - c.getColonne() ; i++)
		    		{
		    			if(Case.getCase(this.getCase().getLigne() + i, this.getCase().getColonne() - i).getPresence())
		    			{
		    				return false;
		    			}
		    		}
		    		return true;
		    	}
		    	if(this.getCase().getColonne() < c.getColonne()) // vers la droite
		    	{ // gauche vers droite
		    		for(int i = 1 ; i < c.getColonne() - this.getCase().getColonne() ; i++)
		    		{
		    			if(Case.getCase(this.getCase().getLigne() + i, this.getCase().getColonne() + i).getPresence())
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

