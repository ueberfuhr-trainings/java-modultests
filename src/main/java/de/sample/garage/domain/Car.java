package de.sample.garage.domain;

import de.sample.garage.domain.exception.ShiftNotPossibleException;

/**
 * A car that has a gas tank, a gear transmission, a clutch and an engine.
 */
public class Car {

    private final Clutch clutch;
    private final Engine engine;
    private final GasTank gastank;
    private final GearTransmission geartransmission;

    /**
     * Constructor.
     */
    public Car() {
        clutch = new Clutch();
        engine = new Engine();
        gastank = new GasTank(55.0);
        geartransmission = new GearTransmission(2);
    }

    Car(Clutch clutch, Engine engine, GasTank gastank, GearTransmission geartransmission) {
        super();
        this.clutch = clutch;
        this.engine = engine;
        this.gastank = gastank;
        this.geartransmission = geartransmission;
    }

    /**
     * Fills up the tank.
     *
     * @param amountOfFuel the amount of fuel.
     */
    public void fillUp(double amountOfFuel) {
        if (engine.isEngineStarted()) {
            throw new IllegalStateException("The engine is running!");
        }

        if (gastank.isAmountPossible(amountOfFuel)) {
            gastank.fillUp(amountOfFuel);
        }
    }

    public Clutch getClutch() {
        return clutch;
    }

    public Engine getEngine() {
        return engine;
    }

    public GasTank getGastank() {
        return gastank;
    }

    public GearTransmission getGeartransmission() {
        return geartransmission;
    }

    /**
     * Shifts up to the next gear. The initial state is <i>&lt;no-gear&gt;</i>,
     * so the first invocation of this method will lead to the car being in the
     * first gear.
     *
     * @throws IllegalStateException     if the clutch is not pressed
     * @throws ShiftNotPossibleException if the car is already in the maximum gear
     */
    public void shiftUp() throws IllegalStateException, ShiftNotPossibleException {
        if (!clutch.isPressed()) {
            throw new IllegalStateException("Clutch is not pressed. Press the clutch to shift.");
        }

        // Don't do that:
        // try {
        geartransmission.shiftUp();
        // } catch (ShiftNotPossibleException e) {
        //     ErrorHandler.handleError(e);
        // }
    }

    /**
     * Drives the car.
     */
    public void drive() {
        if (!gastank.isEmpty() && !engine.isEngineStarted()) {
            engine.start();
        }
    }

    /**
     * Parks the car.
     */
    public void park() {
        if (engine.isEngineStarted()) {
            engine.stop();
        }
    }

}
