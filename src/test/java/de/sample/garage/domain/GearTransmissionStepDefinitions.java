package de.sample.garage.domain;

import de.sample.garage.domain.exception.ShiftNotPossibleException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.Assertions.assertThat;

public class GearTransmissionStepDefinitions {

    // default value: gear transmission with 2 gears
    private GearTransmission gt = new GearTransmission(2);
    private ShiftNotPossibleException exception;

    @Given("we have a gear transmission with {int} gears")
    public void setup(int maxGears) {
        gt = new GearTransmission(maxGears);
    }

    @When("we shift up {int} times")
    public void shifUp(int times) {
        try {
            for (int i = 1; i <= times; i++) {
                gt.shiftUp();
            }
        } catch (ShiftNotPossibleException e) {
            exception = e;
        }
    }

    @Then("the gear transmission has current gear of {int}")
    public void checkCurrentGear(int expected) {
        assertThat(gt)
          .extracting(GearTransmission::getCurrentGear)
          .isEqualTo(expected);
    }

    @Then("the shift up fails")
    public void checkForException() {
        assertThat(exception).isNotNull();
    }
}
