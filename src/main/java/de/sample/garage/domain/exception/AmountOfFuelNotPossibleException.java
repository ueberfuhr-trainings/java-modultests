package de.sample.garage.domain.exception;

/**
 * This exception occurs when the amount of fuel is not possible during fill-up
 * of the gas tank.
 */
public class AmountOfFuelNotPossibleException extends RuntimeException {

    private final double amountOfFuel;
    private final double maxAmountOfFuel;

    /**
     * Constructor.
     */
    public AmountOfFuelNotPossibleException(double amountOfFuel, double maxAmountOfFuel) {
        super("Filling up the tank is not possible!");
        this.amountOfFuel = amountOfFuel;
        this.maxAmountOfFuel = maxAmountOfFuel;
    }

    public double getAmountOfFuel() {
        return amountOfFuel;
    }

    public double getMaxAmountOfFuel() {
        return maxAmountOfFuel;
    }

}
