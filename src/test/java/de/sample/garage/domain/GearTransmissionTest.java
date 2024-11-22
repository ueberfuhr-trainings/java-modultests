package de.sample.garage.domain;

import de.sample.garage.domain.exception.ShiftNotPossibleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class GearTransmissionTest {

    @ParameterizedTest(name = "max gear = {0}")
    @ValueSource(ints = {-5, 0})
    @DisplayName("maximum gear must be greater than zero")
    void shouldConstructorThrowIllegalArgumentException(int maxGear) {
        assertThatThrownBy(() -> new GearTransmission(maxGear))
                .isInstanceOf(IllegalArgumentException.class);
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

    @ParameterizedTest(name = "max gear = {0}")
    @ValueSource(ints = {1, 6, 10})
    @DisplayName("shift up successfully")
    void shouldShiftUpSuccessfully(int maxGear) throws ShiftNotPossibleException {
        final var transmission = new GearTransmission(maxGear);
        for (int i = 1; i <= transmission.getMaxGear(); i++) {
            transmission.shiftUp();
            assertThat(transmission.getCurrentGear())
                    .isEqualTo(i);
        }
    }

    @ParameterizedTest(name = "max gear = {0}")
    @ValueSource(ints = {1, 6, 10})
    @DisplayName("shifting up too much is not possible")
    void shouldThrowShiftNotPossibleException(int maxGear) throws ShiftNotPossibleException {
        final var transmission = new GearTransmission(maxGear);
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
