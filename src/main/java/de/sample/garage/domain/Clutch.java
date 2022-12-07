package de.sample.garage.domain;

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

    public boolean isPressed() {
        return pressed;
    }

}
