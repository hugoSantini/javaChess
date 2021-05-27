package projet_echecs;

public class Roi extends Piece
{

	public Roi(char couleur)
	{
		super(couleur, "R");
	}
	
	public boolean deplacementOk(Case c)
	{
    	if(     (c.getLigne() == this.getCase().getLigne() || c.getLigne() == this.getCase().getLigne() - 1 || c.getLigne() == this.getCase().getLigne() + 1)
    			&&
    			(-1 <= c.getColonne() - this.getCase().getColonne() && c.getColonne() - this.getCase().getColonne() <= 1))
    	{
    		return true;
    	}
    	return false;
    }
}	
