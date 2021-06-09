package projet_echecs;

public class IdentifiantCaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IdentifiantCaseException(String message ) {
		super("Probleme avec l'identifiant de case : "  +message);
	}
}
