package projet_echecs;

import java.util.ArrayList;

public class Tour extends Piece {

	public Tour(char couleur) {
		super(couleur, "T");
	}
	
	
	public boolean deplacementOk(Case c)
	{
    	if(c.getLigne() == this.getCase().getLigne() || c.getColonne() == this.getCase().getColonne())
    	{
    		return true;
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