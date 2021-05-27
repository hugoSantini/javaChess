package projet_echecs;

public class Dame extends Piece
{

	public Dame(char couleur)
	{
		super(couleur, "D");
	}
	
	public boolean deplacementOk(Case c)
	{
    	if(c.getLigne() == this.getCase().getLigne() || c.getColonne() == this.getCase().getColonne())
    	{
    		return true;
    	}
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
