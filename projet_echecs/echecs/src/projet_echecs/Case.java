package projet_echecs;

public class Case {
	
	private int Ligne;
	private char Colonne;
	private Piece piece;
	
	public Case()
	{	
	}
	
	public Case(int L, char C)
	{
		this.Ligne = L;
		this.Colonne = C;
	}

	public int getLigne() {
		return Ligne;
	}

	public void setLigne(int ligne) {
		this.Ligne = ligne;
	}

	public int getColonne() {
		return Colonne;
	}

	public void setColonne(char colonne) {
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
