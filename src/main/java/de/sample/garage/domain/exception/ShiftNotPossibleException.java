package de.sample.garage.domain.exception;

/**
 * This exception occurs when a shift is not possible.
 */
public class ShiftNotPossibleException extends Exception {

    public ShiftNotPossibleException() {
        super("The shift is not possible!");
    }
}
