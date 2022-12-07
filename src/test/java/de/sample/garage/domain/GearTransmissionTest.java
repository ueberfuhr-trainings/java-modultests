package de.sample.garage.domain;

import de.sample.garage.domain.exception.ShiftNotPossibleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GearTransmissionTest {

    private static final int MAXGEAR = 6;

    private final GearTransmission transmission = new GearTransmission(GearTransmissionTest.MAXGEAR);

    @Test
    @DisplayName("constructor should throw IllegalArgumentException")
    @PerformanceMeasuringTest
    void shouldConstructorThrowException() {
        assertAll(
          () -> assertThrows(IllegalArgumentException.class, () -> new GearTransmission(-5)),
          () -> assertThrows(IllegalArgumentException.class, () -> new GearTransmission(0))
        );
    }

    @Test
    @DisplayName("should have correct initial state")
    void shouldHaveCorrectInitialState() {
        assertAll(
          () -> assertNull(transmission.getCurrentGear()),
          () -> assertEquals(GearTransmissionTest.MAXGEAR, transmission.getMaxGear())
        );
    }

    @Test
    @DisplayName("should shift up successfully")
    void shouldShiftUpSuccessfully() throws ShiftNotPossibleException {
        for (int i = 1; i <= transmission.getMaxGear(); i++) {
            transmission.shiftUp();
            assertEquals(i, transmission.getCurrentGear());
        }
    }

    @Test
    @DisplayName("should throw exception when shifting up too much")
    @PerformanceMeasuringTest
    void shouldThrowShiftNotPossibleException() throws ShiftNotPossibleException {
        for (int i = 1; i <= GearTransmissionTest.MAXGEAR; i++) {
            transmission.shiftUp();
        }
        assertThrows(ShiftNotPossibleException.class, transmission::shiftUp);
        // again, to test that the exception is thrown repeatedly
        assertThrows(ShiftNotPossibleException.class, transmission::shiftUp);
    }

}
