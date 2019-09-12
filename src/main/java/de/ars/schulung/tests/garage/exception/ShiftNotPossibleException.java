package de.ars.schulung.tests.garage.exception;

/**
 * This exception occurs when a shift is not possible.
 */
public class ShiftNotPossibleException extends Exception {
	private static final long serialVersionUID = 2307841368639207309L;

	/**
	 * Constructor.
	 */
	public ShiftNotPossibleException() {
		super("The shift is not possible!");
	}
}
