package de.seerobben.be.aufg26.dynamicBinding;

public class KeinDienstException extends Exception {

	/**
	 * ID
	 */
	private static final long serialVersionUID = 1L;
	public static final String EXCEPTION_MESSAGE = "Kein Dienst gefunden";
	
	public KeinDienstException(String exceptionMessage) {
		super(exceptionMessage);
	}

}
