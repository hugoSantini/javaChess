package projet_echecs;

public class Dame extends Piece{
	
	private int[][] CoDame = {{1,1},{-1,1},{1,-1},{-1,-1},{1,0},{0,1},{-1,0},{0,-1}};

	public Dame(Case pos, char couleur) {
		super(pos, couleur, "D" + couleur);
		this.Co = new int[this.CoDame.length][2];
		for(int i = 0; i < this.CoDame.length; i++)
		{
			this.Co[i] = this.CoDame[i];
		}
	}
}
