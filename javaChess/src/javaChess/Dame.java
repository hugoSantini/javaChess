package projet_echecs;

public class Dame extends Piece{
	
	private int[][] Co = {{1,1},{-1,1},{1,-1},{-1,-1},{1,0},{0,1},{-1,0},{0,-1}};

	public Dame(Case pos, char couleur) {
		super(pos, couleur, "D" + couleur);
	}
}
