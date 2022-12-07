package de.sample.garage.domain;

import java.util.logging.Logger;

import de.sample.garage.domain.exception.AmountOfFuelNotPossibleException;

/**
 * A gas tank.
 */
public class GasTank {

    private static final Logger logger = Logger.getLogger("GasTank");

    private final double capacity;
    private double fuel;

    public GasTank(double capacity) {
        this.capacity = capacity;
    }

    public void fillUp(double amountOfFuel) {
        if (!isAmountPossible(amountOfFuel)) {
            throw new AmountOfFuelNotPossibleException(amountOfFuel,
              getCapacity());
        }
        logger.info("Filling up " + amountOfFuel + " litres of fuel.");
        fuel += amountOfFuel;
    }

    public double getCapacity() {
        return capacity;
    }

    public double getFuel() {
        return fuel;
    }

    public boolean isAmountPossible(double amountOfFuel) {
        return amountOfFuel + fuel < getCapacity();
    }

    public boolean isEmpty() {
        return fuel <= 0;
    }
}
