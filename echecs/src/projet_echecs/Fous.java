package projet_echecs;

public class Fous extends Piece{
	
	private int[][] Co = {{1,1},{1,-1},{-1,1},{-1,-1}};

	public Fous(Case pos, char couleur) {
		super(pos, couleur, "F" + couleur);
	}
}
