package de.sample.garage.domain;

public class GasStationAttendant {

    // fill the tank in 5 litres increments
    private static final double FILL_UP_STEPS = 5.0;

    public void fillUpCar(Car car, double amount) {
        double sum = 0;
        while (sum < amount) {
            double currentStep = Math.min(FILL_UP_STEPS, amount - sum);
            car.fillUp(currentStep);
            sum += currentStep;
        }
    }

}
