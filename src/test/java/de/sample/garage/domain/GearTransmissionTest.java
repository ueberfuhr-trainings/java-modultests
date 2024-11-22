package de.sample.garage.domain;

import de.sample.garage.domain.exception.ShiftNotPossibleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class GearTransmissionTest {

    @Test
    @DisplayName("maximum gear must be greater than zero")
    void shouldConstructorThrowIllegalArgumentException() {
        assertAll(
                () -> assertThatThrownBy(() -> new GearTransmission(-5))
                        .isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> new GearTransmission(0))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("correct initial state")
    void shouldHaveCorrectInitialState() {
        final var transmission = new GearTransmission(6);
        assertAll(
                () -> assertThat(transmission.getCurrentGear())
                        .isNull(),
                () -> assertThat(transmission.getMaxGear())
                        .isEqualTo(6)
        );
    }

    @Test
    @DisplayName("shift up successfully")
    void shouldShiftUpSuccessfully() throws ShiftNotPossibleException {
        final var transmission = new GearTransmission(6);
        for (int i = 1; i <= transmission.getMaxGear(); i++) {
            transmission.shiftUp();
            assertThat(transmission.getCurrentGear())
                    .isEqualTo(i);
        }
    }

    @Test
    @DisplayName("shifting up too much is not possible")
    void shouldThrowShiftNotPossibleException() throws ShiftNotPossibleException {
        final var transmission = new GearTransmission(6);
        for (int i = 1; i <= transmission.getMaxGear(); i++) {
            transmission.shiftUp();
        }
        assertThatThrownBy(transmission::shiftUp)
                .isInstanceOf(ShiftNotPossibleException.class);
        // again, to test that the exception is thrown repeatedly
        assertThatThrownBy(transmission::shiftUp)
                .isInstanceOf(ShiftNotPossibleException.class);
    }

}
