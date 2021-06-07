package projet_echecs;

public class Plateau {
	
	private Piece[][] plateau;
	
	public Plateau()
	{
		this.plateau = new Piece[8][8];
		this.initialize();
	}
	
	public Piece[][] getPlateau()
	{
		return this.plateau;
	}
	
	public Piece getPiece(Case c)
	{
		return this.plateau[c.getLigne()][c.getColonne()];
	}
	
	public Piece getPiece(int x, int y)
	{
			return this.plateau[x][y];
	}
	
	public Roi getRoi(boolean couleur)
	{
		for (int i = 0; i < 8; i++)
		{
			for (Piece p : this.getPlateau()[i])
			{
				if (p instanceof Roi && p.getCouleur() == couleur)
				{
					return (Roi) p;
				}
			}
		}
		if (couleur)
		{
			System.out.println("Pas de roi " + "B");
		}
		else
		{
			System.out.println("Pas de roi " + "N");
		}
		return null;
	}
	
	public void setPiece(Case c, Piece p)
	{
		this.plateau[c.getLigne()][c.getColonne()] = p;
		if (p != null)
		{
			c.setPresence(true);
			p.setCase(c);
		}
		else
		{
			c.setPresence(false);
		}
	}
	
	
	public void deplacer(Piece p, Case c)
	{
		boolean clouage = false;
		
		if (! (p instanceof Roi))
		{ //test si la piece est clouée
			this.setPiece(p.getCase(), null);
			for (int j = 0; j < 8; j++)
			{
				for (Piece piece : this.getPlateau()[j])
				{
					if (piece != null)
					{
						if (piece.getCouleur() != p.getCouleur())
						{
							if (piece.CasesPossible().contains(this.getRoi(p.getCouleur()).getCase()))
							{
								this.setPiece(p.getCase(), p);
								clouage = true;
								System.out.println("La piece est clouée par : " + piece);
							}
						}
					}
				}
			}
			this.setPiece(p.getCase(), p);
		}
		
		if (this.getRoi(p.getCouleur()).getEstEchec() && !(p instanceof Roi)) //test si roi en echec et piece qui veut etre jouer pas le roi
		{
			clouage = true;
		}
		else 
		{
			if (p.CasesPossible().contains(c) && !clouage)
			{
				boolean protege = false;
				
				if (p instanceof Roi)
				{ //controle que la case ou le roi va n'est pas controlée par une pièce adverse
					for (int i = 0; i < 8; i++)
					{
						for (Piece t : this.getPlateau()[i])
						{
							if (t instanceof Pion)
							{
								if (t.getCouleur() != p.getCouleur())
								{
									if (t.deplacementOk(c) && c.getColonne() != t.getCase().getColonne())
									{
										System.out.println("Deplacement du roi en : " + c.getLigne() + "," + c.getColonne() + " impossible, la case est controléee par : " + t + "\n");
										protege = true;
									}
								}
							}
							else if (t != null && t != p)
							{
								if (t.CasesPossible().contains(c))
								{
									System.out.println("Deplacement du roi en : " + c.getLigne() + "," + c.getColonne() + " impossible, la case est controlée par : " + t + "\n");
									protege = true;
								}
							}
						}
					}
				}
				if (c.getPresence() && !protege)
				{//controle si la case destination est occupée
					if (this.getPiece(c).getCouleur() != p.getCouleur())
					{
						this.setPiece(p.getCase(),null);
						this.setPiece(c, p);
						p.setPremierCoup(false);
						this.getRoi(p.getCouleur()).setEstEchec(false);
						if (p.CasesPossible().contains(this.getRoi(!p.getCouleur()).getCase()))
						{
							System.out.println("échecs !! (par " + p + ")");
							this.getRoi(!p.getCouleur()).setEstEchec(true);
						}
						for (int i = 0; i < 8; i++)
						{
							for (Piece t : this.getPlateau()[i])
							{
								if (t != null)
								{
									if (t.getCouleur() == p.getCouleur())
										if (t.CasesPossible().contains(this.getRoi(!p.getCouleur()).getCase()))
										{
											System.out.println("échecs !! (par " + t + ")");
											this.getRoi(!p.getCouleur()).setEstEchec(true);
										}
								}
							}
						}
					}
				}
				else if(!protege)
				{
					this.setPiece(p.getCase(),null);
					this.setPiece(c, p);
					p.setPremierCoup(false);
					this.getRoi(p.getCouleur()).setEstEchec(false);
					if (p.CasesPossible().contains(this.getRoi(!p.getCouleur()).getCase()))
					{
						System.out.println("échecs !! (par " + p + ")");
						this.getRoi(!p.getCouleur()).setEstEchec(true);
					}
					for (int i = 0; i < 8; i++)
					{
						for (Piece t : this.getPlateau()[i])
						{
							if (t != null)
							{
								if (t.getCouleur() == p.getCouleur())
									if (t.CasesPossible().contains(this.getRoi(!p.getCouleur()).getCase()))
									{
										System.out.println("échecs !! (par " + t + ")");
										this.getRoi(!p.getCouleur()).setEstEchec(true);
									}
							}
						}
					}
				}
			}
		}
		System.out.println(this);
	}
	
	
	
	
	
	public String toString()
	{
		String str = "";
		for(int i=0 ; i < 8; i+=1)
		{
			//str += String.valueOf(9-(i+1))+ "  "; = vrai ligne 
			str += String.valueOf(i)+ "  "; // visuel pour coordonnées
			for(int j=0 ; j < 8; j+=1)
			{
				if(this.plateau[i][j] != null)
				{
					str+=this.plateau[i][j].toString() + " ";
				}
				else
				{
					str+= "00 ";
				}
			}
			str+="\n";
		}
		//str += "\n   A  B  C  D  E  F  G  H"; = vrai ligne
		str += "\n   0  1  2  3  4  5  6  7\n"; //visuel coordonnées
		return str;
	}
	
	public void viderPlateau()
	{
		for(int i=0 ; i < 8; i+=1)
		{
			for(int j=0 ; j < 8; j+=1)
			{
				this.setPiece(this.plateau[i][j].getCase(), null);
			}
		}
	}

	
	public void initialize()
	{
		Case[][] c = new Case[8][8];
		
		for(int i=0 ; i < 8; i++)
		{
			for(int j=0 ; j < 8; j++)
			{
				 c[i][j] = new Case(i,j);
			}
		}
		
		
		for(int i = 0; i < plateau.length; i++)
		{
			for(int j = 0; j < plateau[i].length; j++)
			{
				plateau[i][j] = null;
			}
		}
		
		
		
		setPiece(c[0][0], new Tour(false));
		setPiece(c[0][1], new Cavalier(false));
		setPiece(c[0][2], new Fous(false));
		setPiece(c[0][3], new Dame(false));
		setPiece(c[0][4], new Roi(false));
		setPiece(c[0][5], new Fous(false));
		setPiece(c[0][6], new Cavalier(false));
		setPiece(c[0][7], new Tour(false));
		
		
		for(char i = 0; i <= 7; i++)
		{
			setPiece(c[1][i], new Pion(false));
			setPiece(c[6][i], new Pion(true));
		}
			setPiece(c[7][0], new Tour(true));
			setPiece(c[7][1], new Cavalier(true));
			setPiece(c[7][2], new Fous(true));
			setPiece(c[7][3], new Dame(true));
			setPiece(c[7][4], new Roi(true));
			setPiece(c[7][5], new Fous(true));
			setPiece(c[7][6], new Cavalier(true));
			setPiece(c[7][7], new Tour(true));
	}
}
