package projet_echecs;

public class Cavalier extends Piece {
	
	private int[][] CoCav = {{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-2},{-1,-2},{-2,-1}};

	public Cavalier(Case pos, char couleur) {
		super(pos, couleur, "C" + couleur);
		this.Co = new int[this.CoCav.length][2];
		for(int i = 0; i < this.CoCav.length; i++)
		{
			this.Co[i] = this.CoCav[i];
		}
	}
}
