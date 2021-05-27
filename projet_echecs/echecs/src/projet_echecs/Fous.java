package projet_echecs;

public class Fous extends Piece{
	

	public Fous(char couleur) {
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
}
