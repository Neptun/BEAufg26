package de.seerobben.be.aufg26.dynamicBinding;

public class FehlerImPrologFileException extends Exception {

	/**
	 * ID
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String EXCEPTION_MESSAGE = "Fehler im Prolog File aufgetreten";
	
	public FehlerImPrologFileException(String exceptionMessage) {
		super(exceptionMessage);
	}

}
