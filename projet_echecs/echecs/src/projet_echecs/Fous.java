package projet_echecs;

public class Fous extends Piece{
	
	private int[][] CoFou = {{1,1},{1,-1},{-1,1},{-1,-1}};

	public Fous(char couleur) {
		super(couleur, "F");
		this.Co = new int[this.CoFou.length][2];
		for(int i = 0; i < this.CoFou.length; i++)
		{
			this.Co[i] = this.CoFou[i];
		}
	}
}
