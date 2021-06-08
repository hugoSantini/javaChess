package projet_echecs;

public class IdentifiantCaseException extends Exception {
	
	public IdentifiantCaseException(String message ) {
		super("Probleme avec l'identifiant de case : "  +message);
	}
	
}
