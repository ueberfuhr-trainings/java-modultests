package de.ars.schulung.tests.garage;

/**
 * A clutch.
 */
public class Clutch {

	private boolean pressed;

	public void press() {
		pressed = true;
	}

	public void release() {
		pressed = false;
	}

	/**
	 * Gibt den Wert pressed zurück.
	 * @return Wert von pressed.
	 */
	public boolean isPressed() {
		return pressed;
	}

}
