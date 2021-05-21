package projet_echecs;

public class Roi extends Piece{
	
	private int[][] CoRoi = {{1,1},{-1,1},{1,-1},{-1,-1},{1,0},{0,1},{-1,0},{0,-1}};

	public Roi(Case pos, char couleur) {
		super(pos, couleur, "R" + couleur);
		this.Co = new int[this.CoRoi.length][2];
		for(int i = 0; i < this.CoRoi.length; i++)
		{
			this.Co[i] = this.CoRoi[i];
		}
	}
}	
