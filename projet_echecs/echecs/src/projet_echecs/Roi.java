package projet_echecs;

public class Roi extends Piece{
	
	private int[][] CoRoi = {{1,1},{-1,1},{1,-1},{-1,-1},{1,0},{0,1},{-1,0},{0,-1}};

	public Roi(char couleur) {
		super(couleur, "R");
		this.Co = new int[this.CoRoi.length][2];
		for(int i = 0; i < this.CoRoi.length; i++)
		{
			this.Co[i] = this.CoRoi[i];
		}
	}
}	
