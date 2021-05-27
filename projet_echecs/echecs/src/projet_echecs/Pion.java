package projet_echecs;

public class Pion extends Piece
{
	private boolean premierCoup;

	public Pion(char couleur) {
		super(couleur, "P");
	}
	
	public boolean deplacementOk(Case c)
	{
		if (this.getPeutPrendre())
		{
			if(this.getCouleur() == 'N')
			{
				if(c.getColonne() == this.getCase().getColonne() + 1 && c.getLigne() == this.getCase().getLigne() + 1)
				{
					return true;
				}
				else if(c.getColonne() == this.getCase().getColonne() - 1 && c.getLigne() == this.getCase().getLigne() + 1)
				{
					return true;
				}
				return false;
			}
			else
			{
				if(c.getColonne() == this.getCase().getColonne() + 1 && c.getLigne() == this.getCase().getLigne() - 1)
				{
					return true;
				}
				else if(c.getColonne() == this.getCase().getColonne() - 1 && c.getLigne() == this.getCase().getLigne() - 1)
				{
					return true;
				}
				return false;
			}
		}
		else
		{
			if(this.getCouleur() == 'N')
			{
				if(c.getColonne() == this.getCase().getColonne() && c.getLigne() == this.getCase().getLigne() + 1)
				{
					return true;
				}
				else if(premierCoup && c.getColonne() == this.getCase().getColonne() && c.getLigne() == this.getCase().getLigne() + 2)
				{
					return true;
				}
				return false;
			}
			else
			{
				if(c.getColonne() == this.getCase().getColonne() && c.getLigne() == this.getCase().getLigne() - 1)
				{
					return true;
				}
				else if(premierCoup && c.getColonne() == this.getCase().getColonne() && c.getLigne() == this.getCase().getLigne() - 2)
				{
					return true;
				}
				return false;
			}
		}
	}
}
