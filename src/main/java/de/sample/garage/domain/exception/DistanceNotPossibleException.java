package de.sample.garage.domain.exception;

/**
 * This exception occurs when the driving the distance is not possible.
 */
public class DistanceNotPossibleException extends RuntimeException {

    private final int distance;

    /**
     * Constructor.
     */
    public DistanceNotPossibleException(int distance) {
        super("Driving the distance is not possible!");
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

}
