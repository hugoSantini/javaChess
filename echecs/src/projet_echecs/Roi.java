package projet_echecs;

public class Roi extends Piece{

	private int[][] Co = {{1,1},{-1,1},{1,-1},{-1,-1},{1,0},{0,1},{-1,0},{0,-1}};

	public Roi(Case pos, char couleur) {
		super(pos, couleur, "R" + couleur);
	}
}	
