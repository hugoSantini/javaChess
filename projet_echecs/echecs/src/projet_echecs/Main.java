package projet_echecs;

public class Main 
{
	public static void main(String[] args)
	{
		Plateau p = new Plateau();
		System.out.println(p);
		Piece m = p.getRoi('B');
		p.deplacer(p.getPiece(1,2), Case.getCase(3, 2));
		System.out.println(p);
	}
}
