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
		this.presence = false;
		
		Cases[L][C] = this;
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
	
	public char getNomColonne()
	{
		switch(this.Colonne)
		{
			case 0 : return 'A' ;
			case 1 : return 'B' ;
			case 2 : return 'C' ;
			case 3 : return 'D' ;
			case 4 : return 'E' ;
			case 5 : return 'F' ;
			case 6 : return 'G' ;
			case 7 : return 'H' ;
			default : return 'O' ;
		}
	}
	public int getNomLigne() {
		switch(this.Ligne)
		{
			case 0 : return 8 ;
			case 1 : return 7 ;
			case 2 : return 6 ;
			case 3 : return 5 ;
			case 4 : return 4 ;
			case 5 : return 3 ;
			case 6 : return 2 ;
			case 7 : return 1 ;
			default : return 'O' ;
		}
	}

	public String toString()
	{
		return String.valueOf(this.Ligne) + " " + String.valueOf(this.Colonne);
	}
}
