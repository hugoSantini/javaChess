package projet_echecs;

import java.util.ArrayList;

public class Tour extends Piece {
	
	private int[][] CoTour = {{1,0},{0,1},{-1,0},{0,-1}};

	public Tour(char couleur) {
		super(couleur, "T");
		this.Co = new int[this.CoTour.length][2];
		for(int i = 0; i < this.CoTour.length; i++)
		{
			this.Co[i] = this.CoTour[i];
		}
	}
	
	public boolean deplacementOk(Case c)
	{
		for(int i = 0;i <=8;i++){
    		if(i == c.getLigne() && c.getColonne() == this.getCase().getColonne()){
    			return true;
    		}
    		if(i == c.getColonne() && c.getLigne() == this.getCase().getLigne()){
    			return true;
    		}
    	}
    	return false;
    }
	
	//Renvoie un boolean en fonction de si le deplacement de la piece courante vers la case c est possible (en tenant compte des autres pieces et en fesant le test deplacement ok)
	public boolean deplacementPossible(Case c)
	{
		return false;
	}
	
	
	//Renvoie la liste des cases repondant a deplacement possible
	public ArrayList<Case> CasesPossible()
	{
		ArrayList<Case> CaseP = new ArrayList<Case>();
		return CaseP;
	}
}