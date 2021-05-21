package projet_echecs;

public class Cavalier extends Piece {
	
	private int[][] Co = {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};

	public Cavalier(Case pos, char couleur) {
		super(pos, couleur, "C" + couleur);
	}
}
