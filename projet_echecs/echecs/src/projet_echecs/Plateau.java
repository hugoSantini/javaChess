package projet_echecs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Plateau implements Serializable {
	
	/* -------------------------------------
	 *  Variable d'instance
	 ------------------------------------- */
	
	private static final long serialVersionUID = 1L;
	private Piece[][] plateau;
	
	/* -------------------------------------
	 *  Constructeur
	 ------------------------------------- */
	/**
	 * Methode constructeur de la class Plateau.
	 */
	public Plateau()
	{
		this.plateau = new Piece[8][8];
		this.initialize();
	}
	
	/* -------------------------------------
	 *  Accesseurs
	 ------------------------------------- */
	
	private Piece[][] getPlateau()
	{
		return this.plateau;
	}
	/**
	 * Getter d'une pièce dans plateau.
	 * @param c Case de la piece en question.
	 */
	public Piece getPiece(Case c)
	{
		return this.plateau[c.getLigne()][c.getColonne()];
	}
	/**
	 * Getter d'une piece dans plateau.
	 * @param x Abscisse de la case de la pièce en question.
	 * @param y Ordonnée de la case de la piece en question.
	 */
	public Piece getPiece(int x, int y)
	{
			return this.plateau[x][y];
	}
	
	private Roi getRoi(boolean couleur)
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
	
	private void setPiece(Case c, Piece p)
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
	
	/* -------------------------------------
	 *  Méthode de classe
	 ------------------------------------- */
	
	private void promotionDame(Piece p)
	{
		p = new Dame(p.getCouleur());
	}
	
	private void promotionTour(Piece p)
	{
		this.setPiece(p.getCase(), new Tour(p.getCouleur()));
	}
	
	private void promotionCavalier(Piece p)
	{
		p = new Cavalier(p.getCouleur());
	}
	
	private void promotionFou(Piece p)
	{
		this.setPiece(p.getCase(), new Fous(p.getCouleur()));
	}
	
	//test si la piece passée en paramêtre est clouée (true = clouée)
	/**
	 * Test si la piece passée en paramêtre est clouée.
	 * @param p Pièce sujète au test de clouage.
	 * @retrun Un boolean traduisant le clouage d'une pièce.
	 */
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
									System.out.println("La piece " + p + " est clouée par : " + piece);
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
	/**
	 * Test si la case d'arrivé d'un pièce clouée est légale. (Exemple de cas d'utilisation : RoiB en a1, DameN en h8 et un fouB en b2, le fou veux allez en e5.)
	 * @param p Pièce sujète au test de clouage.
	 * @param c Case d'arrivé a tester.
	 * @retrun Un boolean traduisant la légaliter du coup.
	 */
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
	
	/**
     * Liste les deplacement de p permet de bloquer un échecs (Déplacement d'une autre pièce que le roi, tout de même permetant de sortir d'un echec)
	 * @param p Piece dont on veux tester les coups.
	 * @retrun Une ArrayList listant tout coups.
	 */
	
    private ArrayList<Case> bloqueEchec(Piece p)
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
    
	/**
     * Test si le déplacement d'une pièce bloque un échec.
	 * @param p Piece dont on veux tester un coup.
	 * @param c Case d'arriver du coup.
	 * @retrun Un boolean traduisant le bloquage d'un échec.
	 */
    
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
	
	/**
     * Test si une piece de couleur opposée à celle en paramètre met en échec.
	 * @param couleur Un boolean tradisant de la couleur qui met en échec.
	 * @retrun Un boolean traduisant un état d'échec.
	 */
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
	
	
	/**
     * Contrôle que la case où le roi va n'est pas controlée par une pièce adverse.
	 * @param p Roi que l'on veux tester.
	 * @param c Case à tester.
	 * @retrun Un boolean traduisant le controle de la case par une pièce adverse. (True = controlée)
	 */
	private boolean deplacementRoi(Roi p, Case c)
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
							protege = true;
						}
					}
				}
			}
		}	
		return protege;
	}
	
	
	/**
     * Donne une liste des cases possible pour le roi passé en paramètre.
	 * @param p Roi que l'on veux tester.
	 * @retrun Une ArrayList de Case possible pour le roi. 
	 */
	
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
	
	//Test si un joeur est en échecs et mat
    private boolean testMat()
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
            	}
            }
        }
        
        if (this.deplacementRoiPossible(this.getRoi(true)).isEmpty() && this.getRoi(true).getEstEchec() && bloque == false)
        {
        	System.out.println("échecs et mat!");
            return true;
        }
        else if (this.deplacementRoiPossible(this.getRoi(false)).isEmpty() && this.getRoi(false).getEstEchec() && bloque == false)
        {
        	System.out.println("échecs et mat!");
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

	/**
     * Effectue le déplacement d'une pièce dans plateau.
	 * @param p Piece que l'on veux déplacer.
	 * @param c Case cible du déplacement.
	 */
	public void deplacement(Piece p, Case c)
	{
		this.setPiece(p.getCase(),null);
		this.setPiece(c, p);
		p.setPremierCoup(false);
		this.getRoi(p.getCouleur()).setEstEchec(false);
	}
	
	
	/**
     * Test la légalité d'un déplacement dans le plateau.
	 * @param p Piece que l'on veux déplacer.
	 * @param c Case cible du déplacement.
	 * @retrun Un boolean traduisant la légalité du coup testé.
	 * 
	 */
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
	
	//Renvoie une liste des cases possible pour la pièce passée en paramètre (tenant compte des pièces adverses)
    private ArrayList<Case> deplacementPiecePossible(Piece p)
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
    
	private void promotion(Piece p, Case c)
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
	
	/**
     * Renvoie le plateau de pièces.
	 * @retrun Un String du plateau.
	 * 
	 */
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
	
	private void viderPlateau()
	{
		for(int i=0 ; i < 8; i+=1)
		{
			for(int j=0 ; j < 8; j+=1)
			{
				this.setPiece(this.plateau[i][j].getCase(), null);
			}
		}
	}

	
	private void initialize()
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
	
	/**
     * Test si le plateau à un état de mat ou de pat.
	 * @retrun Un boolean traduisant l'état de mat ou de pat d'un des joueur.
	 * 
	 */
	public boolean matOrPatTest(boolean couleur) {
		return this.testPat(couleur) || this.testMat();
	}
}
