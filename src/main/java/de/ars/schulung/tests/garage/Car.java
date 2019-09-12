package de.ars.schulung.tests.garage;

import de.ars.schulung.tests.garage.errorhandling.ErrorHandler;
import de.ars.schulung.tests.garage.exception.ShiftNotPossibleException;

/**
 * A car that has a gas tank, a gear transmission, a clutch and an engine.
 */
public class Car {

	private Clutch clutch;
	private Engine engine;
	private GasTank gastank;
	private GearTransmission geartransmission;

	/**
	 * Constructor.
	 */
	public Car() {
		engine = new Engine();
		gastank = new GasTank(55.0);
		geartransmission = new GearTransmission(2);
		clutch = new Clutch();
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
	 * @param amountOfFuel
	 *            the amount of fuel.
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

	public void setClutch(Clutch clutch) {
		this.clutch = clutch;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}

	public void setGastank(GasTank gastank) {
		this.gastank = gastank;
	}

	public void setGeartransmission(GearTransmission geartransmission) {
		this.geartransmission = geartransmission;
	}

	/**
	 * Shifts up to the next gear. The initial state is <i>&lt;no-gear&gt;</i>,
	 * so the first invocation of this method will lead to the car being in the
	 * first gear.
	 * 
	 * @throws IllegalStateException
	 *             if the clutch is not pressed
	 * @throws ShiftNotPossibleException
	 *             if the car is already in the maximum gear
	 */
	public void shiftUp() throws IllegalStateException, ShiftNotPossibleException {
		if (!clutch.isPressed()) {
			throw new IllegalStateException("Die Kupplung ist nich gedrückt!");
		}

		try {
			geartransmission.shiftUp();
		} catch (ShiftNotPossibleException e) {
			ErrorHandler.handleError(e);
		}
	}

	/**
	 * Drives the car.
	 */
	public void drive() {
		if (gastank.isEmpty() && !engine.isEngineStarted()) {
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
