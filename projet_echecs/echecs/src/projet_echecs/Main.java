package projet_echecs;

public class Main 
{
	public static void main(String[] args)
	{
		Plateau Plat = new Plateau();
		Plat.initialize();
		System.out.println(Plat.afficher());
		Plat.deplacementP();
	}
}
