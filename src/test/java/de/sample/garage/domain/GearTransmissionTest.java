package de.sample.garage.domain;

import de.sample.garage.domain.exception.ShiftNotPossibleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GearTransmissionTest {

    @Test
    @DisplayName("maximum gear must be greater than zero")
    void shouldConstructorThrowIllegalArgumentException() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new GearTransmission(-5)),
                () -> assertThrows(IllegalArgumentException.class, () -> new GearTransmission(0))
        );
    }

    @Test
    @DisplayName("correct initial state")
    void shouldHaveCorrectInitialState() {
        final var transmission = new GearTransmission(6);
        assertAll(
                () -> assertNull(transmission.getCurrentGear()),
                () -> assertEquals(6, transmission.getMaxGear())
        );
    }

    @Test
    @DisplayName("shift up successfully")
    void shouldShiftUpSuccessfully() throws ShiftNotPossibleException {
        final var transmission = new GearTransmission(6);
        for (int i = 1; i <= transmission.getMaxGear(); i++) {
            transmission.shiftUp();
            assertEquals(i, transmission.getCurrentGear());
        }
    }

    @Test
    @DisplayName("shifting up too much is not possible")
    void shouldThrowShiftNotPossibleException() throws ShiftNotPossibleException {
        final var transmission = new GearTransmission(6);
        for (int i = 1; i <= transmission.getMaxGear(); i++) {
            transmission.shiftUp();
        }
        assertThrows(ShiftNotPossibleException.class, transmission::shiftUp);
        // again, to test that the exception is thrown repeatedly
        assertThrows(ShiftNotPossibleException.class, transmission::shiftUp);
    }

}
