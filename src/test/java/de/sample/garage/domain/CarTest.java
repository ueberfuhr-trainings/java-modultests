package de.sample.garage.domain;

import de.sample.garage.domain.errorhandling.ErrorHandler;
import de.sample.garage.domain.exception.ShiftNotPossibleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarTest {

    @Mock
    Engine engine;
    @Mock
    GearTransmission transmission;
    @Mock
    GasTank tank;
    @Mock
    Clutch clutch;
    @Mock
    ErrorHandler errorHandler;
    // Class under Test
    @InjectMocks
    Car car;

    @Nested
    @DisplayName("fill-up tests")
    class FillUpTests {

        @Test
        void shouldNotFillUpWhenEngineIsStarted() {
            // Arrange / Given
            when(engine.isEngineStarted()).thenReturn(true);
            lenient().when(tank.isAmountPossible(5.0)).thenReturn(true);
            // Act+Assert
            assertThatThrownBy(() -> car.fillUp(5.0))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("The engine is running!");
            // Assert
            verify(tank, never()).fillUp(anyDouble());
        }

        @Test
        void shouldNotFillUpWithAmountNotPossible() {
            // arrange
            lenient().when(engine.isEngineStarted()).thenReturn(false);
            when(tank.isAmountPossible(anyDouble())).thenReturn(false);
            // act
            car.fillUp(5d);
            // assert
            verify(tank, never()).fillUp(anyDouble());
        }

        @Test
        void shouldFillUp() {
            // arrange
            final double amount = 5d;
            when(engine.isEngineStarted()).thenReturn(false);
            when(tank.isAmountPossible(anyDouble())).thenReturn(true);
            // act
            car.fillUp(amount);
            // assert
            verify(tank).fillUp(amount);
        }
    }

    @Nested
    @DisplayName("shift-up tests")
    class ShiftUpTests {

        @Test
        void shouldShiftUp() throws ShiftNotPossibleException {
            // arrange
            when(clutch.isPressed()).thenReturn(true);
            // act
            car.shiftUp();
            // assert
            verify(transmission).shiftUp();
        }

        @Test
        void shouldNotShiftUpWithClutchNotPressed() throws ShiftNotPossibleException {
            // arrange
            when(clutch.isPressed()).thenReturn(false);
            // act+assert
            assertThrows(IllegalStateException.class, car::shiftUp);
            // assert
            verify(transmission, never()).shiftUp();
        }

        @Test
        void shouldThrowShiftUpNotPossibleException() throws ShiftNotPossibleException {
            // arrange
            when(clutch.isPressed()).thenReturn(true);
            doThrow(ShiftNotPossibleException.class).when(transmission).shiftUp();
            // act+assert
            assertThrows(ShiftNotPossibleException.class, car::shiftUp);
        }

    }

    @Nested
    @DisplayName("drive tests")
    class DriveTests {

        @Test
        void shouldDrive() {
            // arrange
            when(tank.isEmpty()).thenReturn(false);
            when(engine.isEngineStarted()).thenReturn(false);
            // act
            car.drive();
            // assert
            verify(engine).start();
        }

        @Test
        void shouldNotStartEngineWithEmptyGasTank() {
            // arrange
            when(tank.isEmpty()).thenReturn(true);
            lenient().when(engine.isEngineStarted()).thenReturn(false);
            // act
            car.drive();
            // assert
            verify(engine, never()).start();
        }

        @Test
        void shouldNotStartEngineIfAlreadyStarted() {
            // arrange
            lenient().when(tank.isEmpty()).thenReturn(false);
            when(engine.isEngineStarted()).thenReturn(true);
            // act
            car.drive();
            // assert
            verify(engine, never()).start();
        }

    }

    @Nested
    @DisplayName("park tests")
    class ParkTests {

        @Test
        void shouldStopEngine() {
            // arrange
            when(engine.isEngineStarted()).thenReturn(true);
            // act
            car.park();
            // assert
            verify(engine).stop();
        }

        @Test
        void shouldNotStopEngineIfAlreadyStopped() {
            // arrange
            when(engine.isEngineStarted()).thenReturn(false);
            // act
            car.park();
            // assert
            verify(engine, never()).stop();
        }

    }

}
