package projet_echecs;

public class Tour extends Piece {
	
	private int[][] CoTour = {{1,0},{0,1},{-1,0},{0,-1}};

	public Tour(Case pos, char couleur) {
		super(pos, couleur, "T" + couleur);
		this.Co = new int[this.CoTour.length][2];
		for(int i = 0; i < this.CoTour.length; i++)
		{
			this.Co[i] = this.CoTour[i];
		}
	}
}
