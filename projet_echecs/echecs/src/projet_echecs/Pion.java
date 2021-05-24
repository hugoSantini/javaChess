package projet_echecs;

public class Pion extends Piece{
	
	private int[][] CoPion = {{1,1},{-1,1},{0,2},{0,1}};

	public Pion(char couleur) {
		super(couleur, "P");
		this.Co = new int[this.CoPion.length][2];
		for(int i = 0; i < this.CoPion.length; i++)
		{
			this.Co[i] = this.CoPion[i];
		}
	}
	
	public boolean deplacementOk()
	{
		return true;
	}
}
