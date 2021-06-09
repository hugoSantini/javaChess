package projet_echecs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Plateau implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	//test si la piece pass�e en param�tre est clou�e (true = clou�e)
	public boolean clouage(Piece p)
	{
		boolean clouage = false;
		if (!this.estEchec(p.getCouleur()))
		{
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
									System.out.println("La piece " + p + " est clou�e par : " + piece);
								}
							}
						}
					}
				}
				this.setPiece(p.getCase(), p);
			}
		}
		return clouage;
	}
	
	public boolean testBlocageClouage(Piece p, Case c)
	{
		Piece pieceC = null;
		this.setPiece(p.getCase(), null);
		for (int j = 0; j < 8; j++)
		{
			for (Piece piece : this.getPlateau()[j])
			{
				if (piece != null  && !(pieceC instanceof Pion))
				{
					if (piece.getCouleur() != p.getCouleur())
					{
						if (piece.CasesPossible().contains(this.getRoi(p.getCouleur()).getCase()))
						{
							pieceC = piece;
						}
					}
				}
			}
		}
		this.setPiece(p.getCase(), p);
		
		if (pieceC != null)
		{
			if (c == pieceC.getCase())
			{
				return true;
			}
			else if (pieceC.CasesPossible().contains(c))
			{
				return true;
			}
		}
		
		return false;
	}
	
	//test si roi en echec et piece qui veut �tre jouer pas le roi
    //test si deplacement de p permet de bloquer un �checs (true = blocage possible)
    public ArrayList<Case> bloqueEchec(Piece p)
    {
        ArrayList<Case> caseBloquer = new ArrayList<Case>();

        if (this.getRoi(p.getCouleur()).getEstEchec() && !(p instanceof Roi)) 
        { 
            for (Case ca : this.deplacementPiecePossible(p))
            {
            	//System.out.println("dep de " + p + "{"+p.getCase().getLigne()+","+p.getCase().getColonne()+"} en ["+ca.getLigne()+","+ca.getColonne()+"]" + this.testDep(p, ca) + " bloque : " + this.peutBloquer(p, ca));
                if (this.peutBloquer(p, ca))
                {
                	caseBloquer.add(ca);
                }
            }
        }
        return caseBloquer;
    }
    
    // test si le d�placement de p en c bloque un �checs
    public boolean peutBloquer(Piece p, Case c)
    {
    	boolean peutBloquer = true;
    	Piece pieceB = null;
    	
    	Case caseD = p.getCase();
    	
    	if (this.getPiece(Case.getCase(c.getLigne(), c.getColonne())) != null)
    	{
    		pieceB = this.getPiece(Case.getCase(c.getLigne(), c.getColonne()));
    	}

        if (this.getRoi(p.getCouleur()).getEstEchec() && !(p instanceof Roi)) 
        { 
        	this.setPiece(c,p);
            for (int j = 0; j < 8; j++)
            {
            	for (Piece piece : this.getPlateau()[j])
                {
            		if (piece != null && !(piece instanceof Roi))
                    {
            			if (piece.getCouleur() != p.getCouleur())
                        {
            				if (piece.CasesPossible().contains(this.getRoi(p.getCouleur()).getCase()))
                            {
            					peutBloquer = false;
                            }
                        }
                    }
                }
            }
            this.setPiece(c, pieceB);
            this.setPiece(caseD, p);
        }
        return peutBloquer;
    }
	
	// test si une piece de couleur oppos�e � celle en param�tre met en �checs (true = �checs)
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
							this.getRoi(couleur).setEstEchec(true);
						}
					}
				}
			}
		}
		return this.getRoi(couleur).getEstEchec();
	}
	
	
	//contr�le que la case o� le roi va n'est pas control�e par une pi�ce adverse (true = case prot�g�e)
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
							//System.out.println("D�placement impossible, la case est control�ee par : " + t + "\n");
							protege = true;
						}
					}
				}
				else if (t != null && t != p)
				{
					if (t.getCouleur() != p.getCouleur())
					{
						if (t.CasesPossible().contains(c))
						{
							//System.out.println("D�placement impossible, la case est control�ee par : " + t + "\n");
							protege = true;
						}
					}
				}
			}
		}	
		return protege;
	}
	
	
	//Renvoie une liste des cases possible pour le roi pass� en param�tre (tenant compte des pi�ces adverses)
	public ArrayList<Case> deplacementRoiPossible(Roi p)
	{
		ArrayList<Case> caseRoi = new ArrayList<Case>();
		for (Case c : p.CasesPossible())
		{
			if (!this.deplacementRoi(p, c) && testDep(p, c))
			{
				caseRoi.add(c);
			}
		}
		return caseRoi;
	}
	
	//test si un joeur est en �checs et mat
	//test si un joeur est en �checs et mat
    public boolean testMat()
    {
        boolean bloque = false;

        for (int i = 0; i < 8; i++)
        {
            for (Piece t : this.getPlateau()[i])
            {
            	if (t != null)
            	{
	                if (this.bloqueEchec(t).size() != 0)
	                {
	                    bloque = true;
	                }
	                //System.out.println(t + ":" + this.bloqueEchec(t));
            	}
            }
        }
        
        if (this.deplacementRoiPossible(this.getRoi(true)).isEmpty() && this.getRoi(true).getEstEchec() && bloque == false)
        {
        	System.out.println("�checs et mat!");
            return true;
        }
        else if (this.deplacementRoiPossible(this.getRoi(false)).isEmpty() && this.getRoi(false).getEstEchec() && bloque == false)
        {
        	System.out.println("�checs et mat!");
            return true;
        }
        return false;
    }
	
	//test si un joueur est pat
	public boolean testPat(boolean couleur)
	{
		boolean estPatBlanc = true;
		boolean estPatNoir = true;

			for (int i = 0; i < 8; i++)
			{
				for (Piece t : this.getPlateau()[i])	
				{
					if (t != null)
					{
						if (t.getCouleur())
						{
							if (t instanceof Roi)
							{
								if (!this.deplacementRoiPossible((Roi) t).isEmpty())
								{
									estPatBlanc = false;
								}
							}
							else
							{
								if (this.deplacementPiecePossible(t).size()!=0)
								{
									estPatBlanc = false;
								}
							}
						}
						else
						{
							if (t instanceof Roi)
							{
								if (!this.deplacementRoiPossible((Roi) t).isEmpty())
								{
									estPatNoir = false;
								}
							}
							else
							{
								if (this.deplacementPiecePossible(t).size()!=0)
								{
									estPatNoir = false;
								}
							}
						}
					}
				}
		
			}
		if (couleur)
		{
			if  (estPatBlanc && !this.estEchec(couleur))
			{
				System.out.println("Pat!");
				return true;
			}
		}
		else
		{
			if  (estPatNoir && !this.estEchec(couleur))
			{
				System.out.println("Pat!");
				return true;
			}
		}
		return false;
	}
	
	// effectue un deplacement 
	public void deplacement(Piece p, Case c)
	{
		this.setPiece(p.getCase(),null);
		this.setPiece(c, p);
		p.setPremierCoup(false);
		this.getRoi(p.getCouleur()).setEstEchec(false);
	}
	
	
	//controle si la case destination est valide et est occup�e (true = dep possible)
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
	
	//Renvoie une liste des cases possible pour la pi�ce pass�e en param�tre (tenant compte des pi�ces adverses)
    public ArrayList<Case> deplacementPiecePossible(Piece p)
    {
        ArrayList<Case> casep = new ArrayList<Case>();
        for (Case c : p.CasesPossible())
        {
            if (this.testDep(p, c))
            {
                casep.add(c);
            }
        }
        return casep;
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
			str += String.valueOf(9-(i+1))+ "  ";
			for(int j=0 ; j < 8; j+=1)
			{
				if(this.plateau[i][j] != null)
				{
					str+=this.plateau[i][j].toString() + " ";
				}
				else
				{
					str+= "-- ";
				}
			}
			str+="\n";
		}
		str += "\n   A  B  C  D  E  F  G  H";
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
		
		// set up pat
		/*
		setPiece(c[0][4], new Roi(false));
		setPiece(c[3][3], new Dame(false));
		setPiece(c[5][5], new Tour(false));
		setPiece(c[0][0], new Tour(false));
		
		setPiece(c[6][4], new Roi(true));
		*/
	
	}
	
	
	public boolean matOrPatTest(boolean couleur) {
		return this.testPat(couleur) || this.testMat();
	}
}
