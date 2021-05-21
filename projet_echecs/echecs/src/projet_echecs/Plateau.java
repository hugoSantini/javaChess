package projet_echecs;

import java.util.ArrayList;

public class Plateau {
	
	private Case[][] plateau;
	private static char[] abcisse = {'a','b','c','d','e','f','g','h'};
	
	public Plateau()
	{
		this.plateau = new Case[8][8];
		for(int i=0 ; i < 8; i+=1)
		{
			for(int j=0 ; j < 8; j+=1)
			{
				this.plateau[i][j] = new Case(j,abcisse[i]);
			}
		}
	}
	
	
	public String afficher()
	{
		String str = "";
		for(int i=0 ; i < 8; i+=1)
		{
			str += String.valueOf(9-(i+1))+ "  ";
			for(int j=0 ; j < 8; j+=1)
			{
				str+=this.plateau[i][j].toString() + " ";
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
				this.plateau[i][j].viderCase();
			}
		}
	}
	
	public ArrayList<Case> deplacementP(Piece p)
	{
		ArrayList<Case> caseP = new ArrayList<Case>();
		Case caseD = p.getPosition();
		int i = 0;
		for(int[] c : p.Co)
		{
			while (c[0]*i < 8 && c[1]*1 < 8)
			{
				caseP.add(this.plateau[caseD.getLigne()+ (p.getCo(i)[0])*i][caseD.getColonne()+ (p.getCo(i)[1])*i]);
			}
		}
		return caseP;
	}
	
	
	public void initialize()
	{
		this.viderPlateau();
		
		Tour N1 = new Tour(this.plateau[0][0], 'N');
		this.plateau[0][0].setPiece(N1);
		
		Cavalier N2 = new Cavalier(this.plateau[0][1], 'N');
		this.plateau[0][1].setPiece(N2);
		
		Fous N3 = new Fous(this.plateau[0][2], 'N');
		this.plateau[0][2].setPiece(N3);
		
		Dame N4 = new Dame(this.plateau[0][3], 'N');
		this.plateau[0][3].setPiece(N4);
		
		Roi N5 = new Roi(this.plateau[0][4], 'N');
		this.plateau[0][4].setPiece(N5);
		
		Fous N6 = new Fous(this.plateau[0][5], 'N');
		this.plateau[0][5].setPiece(N6);
		
		Cavalier N7 = new Cavalier(this.plateau[0][6], 'N');
		this.plateau[0][6].setPiece(N7);
		
		Tour N8 = new Tour(this.plateau[0][7], 'N');
		this.plateau[0][7].setPiece(N8);
		
		Pion N9 = new Pion(this.plateau[1][0], 'N');
		this.plateau[1][0].setPiece(N9);
		
		Pion N10 = new Pion(this.plateau[1][1], 'N');
		this.plateau[1][1].setPiece(N10);
		
		Pion N11 = new Pion(this.plateau[1][2], 'N');
		this.plateau[1][2].setPiece(N11);
		
		Pion N12 = new Pion(this.plateau[1][3], 'N');
		this.plateau[1][3].setPiece(N12);
		
		Pion N13 = new Pion(this.plateau[1][4], 'N');
		this.plateau[1][4].setPiece(N13);
		
		Pion N14 = new Pion(this.plateau[1][5], 'N');
		this.plateau[1][5].setPiece(N14);
		
		Pion N15 = new Pion(this.plateau[1][6], 'N');
		this.plateau[1][6].setPiece(N15);
		
		Pion N16 = new Pion(this.plateau[1][7], 'N');
		this.plateau[1][7].setPiece(N16);
		
		
		
		
		Tour B1 = new Tour(this.plateau[7][0], 'B');
		this.plateau[7][0].setPiece(B1);
		
		Cavalier B2 = new Cavalier(this.plateau[7][1], 'B');
		this.plateau[7][1].setPiece(B2);
		
		Fous B3 = new Fous(this.plateau[7][2], 'B');
		this.plateau[7][2].setPiece(B3);
		
		Dame B4 = new Dame(this.plateau[7][3], 'B');
		this.plateau[7][3].setPiece(B4);
		
		Roi B5 = new Roi(this.plateau[7][4], 'B');
		this.plateau[7][4].setPiece(B5);
		
		Fous B6 = new Fous(this.plateau[7][5], 'B');
		this.plateau[7][5].setPiece(B6);
		
		Cavalier B7 = new Cavalier(this.plateau[7][6], 'B');
		this.plateau[7][6].setPiece(B7);
		
		Tour B8 = new Tour(this.plateau[7][7], 'B');
		this.plateau[7][7].setPiece(B8);
		
		Pion B9 = new Pion(this.plateau[6][0], 'B');
		this.plateau[6][0].setPiece(B9);
		
		Pion B10 = new Pion(this.plateau[6][1], 'B');
		this.plateau[6][1].setPiece(B10);
		
		Pion B11 = new Pion(this.plateau[6][2], 'B');
		this.plateau[6][2].setPiece(B11);
		
		Pion B12 = new Pion(this.plateau[6][3], 'B');
		this.plateau[6][3].setPiece(B12);
		
		Pion B13 = new Pion(this.plateau[6][4], 'B');
		this.plateau[6][4].setPiece(B13);
		
		Pion B14 = new Pion(this.plateau[6][5], 'B');
		this.plateau[6][5].setPiece(B14);
		
		Pion B15 = new Pion(this.plateau[6][6], 'B');
		this.plateau[6][6].setPiece(B15);
		
		Pion B16 = new Pion(this.plateau[6][7], 'B');
		this.plateau[6][7].setPiece(B16);
	}
}
