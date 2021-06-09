package projet_echecs;

import java.util.ArrayList;

public class Pion extends Piece
{

	/* -------------------------------------
	 *  Variable d'instance
	 ------------------------------------- */
	private static final long serialVersionUID = 1L;
	private boolean peutPrendre;

	/**
	 * Constructeur de la class Pion.
	 * @param couleur Boolean qui traduit la couleur de la piece (True = Blanc, False = Noir).
	 */
	public Pion(boolean couleur) {
		super(couleur);
		this.peutPrendre = false;
	}
	
	 private void setPeutPrendre()
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
	
	/* -------------------------------------
	 *  Accesseurs
	 ------------------------------------- */

	private void setPeutPrendre(boolean b)
	{
		this.peutPrendre = b;
	}
	
	private boolean getPeutPrendre()
	{
		return this.peutPrendre;
	}
	
	/* -------------------------------------
	 *  Méthode de classe
	 ------------------------------------- */
	/**
	 * Dis si le coup est conforme au déplacement de la pièce.
	 * @param c La case cible du Pion courant.
	 * @return Un boolean qui traduit la possibilité du coup.
	 */
	public boolean deplacementOk(Case c)
	{
		if(!this.getCouleur())
		{
			if(c.getColonne() == this.getCase().getColonne() + 1 && c.getLigne() == this.getCase().getLigne() + 1)
			{
				return true;
			}
			else if(c.getColonne() == this.getCase().getColonne() - 1 && c.getLigne() == this.getCase().getLigne() + 1)
			{
				return true;
			}
			else if(c.getColonne() == this.getCase().getColonne() && c.getLigne() == this.getCase().getLigne() + 1)
			{
				return true;
			}
			else if(this.getPremierCoup() && c.getColonne() == this.getCase().getColonne() && c.getLigne() == this.getCase().getLigne() + 2)
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
			if(c.getColonne() == this.getCase().getColonne() && c.getLigne() == this.getCase().getLigne() - 1)
			{
				return true;
			}
			else if(this.getPremierCoup() && c.getColonne() == this.getCase().getColonne() && c.getLigne() == this.getCase().getLigne() - 2)
			{
				return true;
			}
			return false;
		}
	}
	
	/**
	 * Dis si le coup est conforme au déplacement de la pièce et prend en compte les colisions avec les autres pièces du plateau.
	 * @param c La case cible du Pion courant.
	 * @return Un boolean qui traduit la possibilité du coup avec prise en compte des colisions.
	 */
	public boolean deplacementPossible(Case c)
	{
		if (this.deplacementOk(c)) 
		{
			if (this.getCase().getColonne() == c.getColonne())
			{
				if (!this.getCouleur())
				{
					if (this.getCase().getLigne() + 2 == c.getLigne())
					{
						if (Case.getCase(c.getLigne() - 1, c.getColonne()).getPresence() || c.getPresence())
						{
							return false;
						}
					}
					else
					{
						if (c.getPresence())
						{
							return false;
						}
					}
				}
				else 
				{
					if (this.getCase().getLigne() - 2 == c.getLigne())
					{
						if (Case.getCase(c.getLigne() + 1, c.getColonne()).getPresence() || c.getPresence())
						{
							return false;
						}
					}
					else
					{
						if (c.getPresence())
						{
							return false;
						}
					}
				}
			}
			else
			{
				if(!c.getPresence())
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Fait une liste des cases d'arrivés possibles du Pion.
	 * @return Renvoie la liste des cases repondant à déplacement possible.
	 */
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
			return CaseP;
		}
		return CaseP;
	}
}

