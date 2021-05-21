package projet_echecs;

public class Case {
	
	private char Ligne;
	private int Colonne;
	private Piece piece;
	
	public Case()
	{	
	}
	
	public Case(char L, int C)
	{
		this.Ligne = L;
		this.Colonne = C;
	}

	public char getLigne() {
		return Ligne;
	}

	public void setLigne(char ligne) {
		this.Ligne = ligne;
	}

	public int getColonne() {
		return Colonne;
	}

	public void setColonne(int colonne) {
		this.Colonne = colonne;
	}
	

	public Boolean getPresence() {
		return this.piece != null;
	}

	public Piece getPiece() {
		return this.piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	
	public void viderCase()
	{
		this.piece = null;
	}
	
	public String toString()
	{
		if(this.getPresence())
		{
			return this.piece.toString();
		}
		else
		{
		return "00";
		}
	}
}
