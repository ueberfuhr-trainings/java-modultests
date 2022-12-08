package de.sample.garage.domain;

import de.sample.garage.domain.exception.ShiftNotPossibleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class GearTransmissionTest {

    private static final int MAXGEAR = 6;

    private final GearTransmission transmission = new GearTransmission(GearTransmissionTest.MAXGEAR);

    @Test
    @DisplayName("constructor should throw IllegalArgumentException")
    void shouldConstructorThrowException() {
        assertAll(
          () -> assertThatThrownBy(() -> new GearTransmission(-5))
            .isInstanceOf(IllegalArgumentException.class),
          () -> assertThatThrownBy(() -> new GearTransmission(0))
            .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("should have correct initial state")
    void shouldHaveCorrectInitialState() {
        assertThat(transmission)
          .extracting(GearTransmission::getCurrentGear, GearTransmission::getMaxGear)
          .containsExactly(null, GearTransmissionTest.MAXGEAR);
    }

    @Test
    @DisplayName("should shift up successfully")
    void shouldShiftUpSuccessfully() throws ShiftNotPossibleException {
        for (int i = 1; i <= transmission.getMaxGear(); i++) {
            transmission.shiftUp();
            assertThat(transmission)
              .extracting(GearTransmission::getCurrentGear)
              .isEqualTo(i);
        }
    }

    @Test
    @DisplayName("should throw exception when shifting up too much")
    void shouldThrowShiftNotPossibleException() throws ShiftNotPossibleException {
        for (int i = 1; i <= GearTransmissionTest.MAXGEAR; i++) {
            transmission.shiftUp();
        }
        assertThatThrownBy(transmission::shiftUp)
          .isInstanceOf(ShiftNotPossibleException.class);
        // again, to test that the exception is thrown repeatedly
        assertThatThrownBy(transmission::shiftUp)
          .isInstanceOf(ShiftNotPossibleException.class);
    }

}
