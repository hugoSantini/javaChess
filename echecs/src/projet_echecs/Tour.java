package projet_echecs;

public class Tour extends Piece {
	
	private int[][] Co = {{1,0},{0,1},{-1,0},{0,-1}};

	public Tour(Case pos, char couleur) {
		super(pos, couleur, "T" + couleur);
	}
}
