package projet_echecs;

import java.util.ArrayList;

public class Pion extends Piece
{
	private boolean premierCoup;
	private boolean peutPrendre;

	public Pion(char couleur) {
		super(couleur, "P");
	}
	
	public void setPeutPrendre()
	{
		this.peutPrendre = false;
		for (Case c : this.CasesPossible())
		{
			if (c.getPresence())
			{
				this.peutPrendre = true;
			}
		}
	}
	
	public void setPeutPrendre(boolean b)
	{
		this.peutPrendre = b;
	}
	
	public boolean getPeutPrendre()
	{
		return this.peutPrendre;
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
	
	public boolean deplacementPossible(Case c)
	{
		if (this.deplacementOk(c)) 
		{
			if (this.getCase().getColonne() == c.getColonne())
			{
				return false;
			}
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

