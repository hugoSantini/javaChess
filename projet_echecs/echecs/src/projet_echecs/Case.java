package projet_echecs;

public class Case {
	
	private int Ligne;
	private int Colonne;
	private static Case[][] Cases = new Case[8][8];
	private boolean presence;
	
	public Case(int L, int C)
	{
		this.Ligne = L;
		this.Colonne = C;
		
		Cases[L][C] = new Case(this);
	}
	
	public Case(Case c)
	{
		this.Ligne = c.getLigne();
		this.Colonne = c.getColonne();
	}
	
	public static Case getCase(int L, int C)
	{
		return Cases[L][C];
	}

	public int getLigne() {
		return this.Ligne;
	}

	public void setLigne(int ligne) {
		this.Ligne = ligne;
	}

	public int getColonne() {
		return this.Colonne;
	}
	
	public char getNomColonne()
	{
		switch(this.Colonne)
		{
			case 1 : return 'A' ;
			case 2 : return 'B' ;
			case 3 : return 'C' ;
			case 4 : return 'D' ;
			case 5 : return 'E' ;
			case 6 : return 'F' ;
			case 7 : return 'G' ;
			case 8 : return 'H' ;
			default : return 'O' ;
		}
	}

	public void setColonne(int colonne) {
		this.Colonne = colonne;
	}
	
	public boolean getPresence()
	{
		return this.presence;
	}
	
	public void setPresence(boolean b)
	{
		this.presence = b;
	}
	
	public String toString()
	{
		return "00";
	}
}
