package de.sample.garage.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class GasStationAttendantTest {

    final Car car = mock(Car.class);
    final GasStationAttendant attendant = new GasStationAttendant();

    @Test
    void shouldNotFillUpAnyGas() {
        attendant.fillUpCar(car, 0.0);
        verify(car, never()).fillUp(anyDouble());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 5.0, 5.1, 8.9, 31.8})
    void shouldFillUpWholeGas(final double amount) {
        attendant.fillUpCar(car, amount);
        // cature arguments on invocations
        var amountCaptor = ArgumentCaptor.forClass(Double.class);
        verify(car, atLeastOnce()).fillUp(amountCaptor.capture());
        // assert sum is equal to amount
        var sumOfAmounts = amountCaptor.getAllValues()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();
        assertThat(sumOfAmounts).isEqualTo(amount);
    }

}
