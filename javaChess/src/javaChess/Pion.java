package projet_echecs;

public class Pion extends Piece{
	
	private int[][] Co = {{1,1},{-1,1},{0,2},{0,1}};

	public Pion(Case pos, char couleur) {
		super(pos, couleur, "P" + couleur);
	}
}
