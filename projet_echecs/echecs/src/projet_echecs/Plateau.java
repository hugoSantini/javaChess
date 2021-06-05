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
	
	public Roi getRoi(char c)
	{
		for (int i = 0; i < 8; i++)
		{
			for (Piece p : this.getPlateau()[i])
			{
				if (p instanceof Roi && p.getCouleur() == c)
				{
					return (Roi) p;
				}
			}
		}
		System.out.println("Pas de roi " + c);
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
		if (p.CasesPossible().contains(c) )
		{
			if (c.getPresence())
			{
				if (this.getPiece(c).getCouleur() != p.getCouleur())
				{
					this.setPiece(p.getCase(),null);
					this.setPiece(c, p);
					p.setPremierCoup(false);
				}
			}
			else
			{
				this.setPiece(p.getCase(),null);
				this.setPiece(c, p);
				p.setPremierCoup(false);
				if (p.CasesPossible().contains(this.getRoi('B').getCase()))
				{
					System.out.println("échecs !!");
					this.getRoi('B').setEstEchec(true);
				}
			}
		}
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
		str += "\n   0  1  2  3  4  5  6  7"; //visuel coordonnées
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
		
		
		
		setPiece(c[0][0], new Tour('N'));
		setPiece(c[0][1], new Cavalier('N'));
		setPiece(c[0][2], new Fous('N'));
		setPiece(c[0][3], new Dame('N'));
		setPiece(c[0][4], new Roi('N'));
		setPiece(c[0][5], new Fous('N'));
		setPiece(c[0][6], new Cavalier('N'));
		setPiece(c[0][7], new Tour('N'));
		
		
		for(char i = 0; i <= 7; i++)
		{
			setPiece(c[1][i], new Pion('N'));
			setPiece(c[6][i], new Pion('B'));
		}
		
			setPiece(c[7][0], new Tour('B'));
			setPiece(c[7][1], new Cavalier('B'));
			setPiece(c[7][2], new Fous('B'));
			setPiece(c[7][3], new Dame('B'));
			setPiece(c[4][3], new Roi('B'));
			setPiece(c[7][5], new Fous('B'));
			setPiece(c[7][6], new Cavalier('B'));
			setPiece(c[7][7], new Tour('B'));
	}
}
