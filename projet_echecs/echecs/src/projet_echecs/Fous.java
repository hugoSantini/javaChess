package projet_echecs;

public class Fous extends Piece{
	
	private int[][] CoFou = {{1,1},{1,-1},{-1,1},{-1,-1}};

	public Fous(Case pos, char couleur) {
		super(pos, couleur, "F" + couleur);
		this.Co = new int[this.CoFou.length][2];
		for(int i = 0; i < this.CoFou.length; i++)
		{
			this.Co[i] = this.CoFou[i];
		}
	}
}
