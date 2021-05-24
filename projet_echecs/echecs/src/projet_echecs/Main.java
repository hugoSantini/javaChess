package projet_echecs;

public class Main 
{
	public static void main(String[] args)
	{
		Plateau p = new Plateau();
		System.out.println(p);
		System.out.println(p.getPiece(0,7));
		System.out.println(p.getPiece(0,7).deplacementOk(Case.getCase(4,7)));
	}
}
