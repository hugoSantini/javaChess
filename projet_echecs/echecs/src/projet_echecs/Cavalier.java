package projet_echecs;

public class Cavalier extends Piece {

	public Cavalier(char couleur) 
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
}
