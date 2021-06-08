package projet_echecs;

import java.util.ArrayList;
import java.util.Scanner;

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
	
	public void promotionDame(Piece p)
	{
		p = new Dame(p.getCouleur());
	}
	
	public void promotionTour(Piece p)
	{
		this.setPiece(p.getCase(), new Tour(p.getCouleur()));
	}
	
	public void promotionCavalier(Piece p)
	{
		p = new Cavalier(p.getCouleur());
	}
	
	public void promotionFou(Piece p)
	{
		this.setPiece(p.getCase(), new Fous(p.getCouleur()));
	}
	
	//test si la piece passée en paramêtre est clouée (true = clouée)
	public boolean clouage(Piece p)
	{
		boolean clouage = false;
		if (! (p instanceof Roi) )
		{ 
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
								System.out.println("La piece " + p + " est clouée par : " + piece);
							}
						}
					}
				}
			}
			this.setPiece(p.getCase(), p);
		}
		return clouage;
	}
	
	//test si roi en echec et piece qui veut être jouer pas le roi
	//test si deplacement de p permet de bloquer un échecs (true = blocage possible)
	public boolean bloqueEchec(Piece p, Case c)
	{
		boolean peutBloquer = true;
		
		if (this.getRoi(p.getCouleur()).getEstEchec() && !(p instanceof Roi)) 
		{ 
			this.setPiece(c,p);
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
								peutBloquer = false;
								System.out.println("Votre roi est attaqué par : " + piece);
							}
						}
					}
				}
			}
			this.setPiece(c, null);
		}
		return peutBloquer;
	}
	
	// test si une piece de couleur opposée à celle en paramètre met en échecs (true = échecs)
	public boolean estEchec(boolean couleur)
	{	
		for (int i = 0; i < 8; i++)
		{ 
			for (Piece t : this.getPlateau()[i])
			{
				if (t != null)
				{
					if (t.getCouleur() != couleur)
					{
						if (t.CasesPossible().contains(this.getRoi(couleur).getCase()))
						{
							System.out.println("échecs !! (par " + t + ")");
							this.getRoi(couleur).setEstEchec(true);
						}
					}
				}
			}
		}
		return this.getRoi(couleur).getEstEchec();
	}
	
	
	//contrôle que la case où le roi va n'est pas controlée par une pièce adverse (true = case protégée)
	public boolean deplacementRoi(Roi p, Case c)
	{
		boolean protege = false;
		
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
		return protege;
	}
	
	
	//Renvoie une liste des cases possible pour le roi passé en paramètre (tenant compte des pièces adverses)
	public ArrayList<Case> deplacementRoiPossible(Roi p)
	{
		ArrayList<Case> caseRoi = new ArrayList<Case>();
		for (Case c : p.CasesPossible())
		{
			if (!this.deplacementRoi(p, c))
			{
				caseRoi.add(c);
			}
		}
		return caseRoi;
	}
	
	//test si un joeur est en échecs et mat
	public boolean testMat()
	{
		if (this.deplacementRoiPossible(this.getRoi(true)).isEmpty() && this.getRoi(true).getEstEchec())
		{
			return true;
		}
		else if (this.deplacementRoiPossible(this.getRoi(false)).isEmpty() && this.getRoi(false).getEstEchec())
		{
			return true;
		}
		return false;
	}
	
	//test si un joueur est pat
	public boolean testPat()
	{
		boolean estPat = true;

		for (int i = 0; i < 8; i++)
		{
			for (Piece t : this.getPlateau()[i])
			{
				if (t instanceof Roi)
				{
					if (!this.deplacementRoiPossible((Roi) t).isEmpty())
					{
						estPat = false;
					}
				}
				for (Case ca : t.CasesPossible())
				{
					if (this.testDep(t, ca))
					{
						estPat = false;
					}
				}
			}
		}
		return estPat;
	}
	
	// effectue un deplacement 
	public void deplacement(Piece p, Case c)
	{
		this.setPiece(p.getCase(),null);
		this.setPiece(c, p);
		p.setPremierCoup(false);
		this.getRoi(p.getCouleur()).setEstEchec(false);
	}
	
	
	//controle si la case destination est valide et est occupée (true = dep possible)
	public boolean testDep(Piece p, Case c)
	{
		if (p.CasesPossible().contains(c))
		{
			if (c.getPresence())
			{
				if (this.getPiece(c).getCouleur() != p.getCouleur())
				{
					return true;
				}
			}
			else
			{
				return true;
			}
		}
		return false;
	}
	
	public void promotion(Piece p, Case c)
	{
		if (p instanceof Pion)
		{ // test pour promotion
			if((p.getCouleur() && c.getLigne() == 0) || (!p.getCouleur() && c.getLigne() == 7))
			{
				System.out.println("Promotion?");
				Scanner sc = new Scanner(System.in);
				String prom = sc.nextLine();
				if (prom.equals("Dame"))
				{
					this.promotionDame(p);
				}
				else if (prom.equals("Fous"))
				{
					this.promotionFou(p);
				}
				else if (prom.equals("Tour"))
				{
					this.promotionTour(p);
				}
				else if (prom.equals("Cavalier"))
				{
					this.promotionCavalier(p);
				}
				else
				{
					System.out.println("Promotion invalide");
				}
				sc.close();
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
			
			setPiece(c[2][2], new Pion(true));
	}
}
