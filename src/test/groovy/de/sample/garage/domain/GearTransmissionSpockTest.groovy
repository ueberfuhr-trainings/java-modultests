package de.sample.garage.domain

import de.sample.garage.domain.exception.ShiftNotPossibleException
import spock.lang.Specification

class GearTransmissionSpockTest extends Specification {

  static final def MAXGEAR = 6

  final def transmission = [MAXGEAR] as GearTransmission

  def "constructor should throw IllegalArgumentException when max gear is #maxGear"() {
    when:
    new GearTransmission(maxGear)
    then:
    thrown IllegalArgumentException
    where:
    maxGear << [-5, 0]
  }

  def "should shift up successfully"() {
    when:
    def results = (1..transmission.maxGear).collect {
      transmission.shiftUp()
      transmission.currentGear
    }
    then:
    results == (1..transmission.maxGear)
  }

  def "should throw exception when shifting up too much"() {
    given:
    (1..transmission.maxGear).each {
      transmission.shiftUp()
    }
    when:
    transmission.shiftUp()
    then:
    thrown ShiftNotPossibleException
    and:
    when:
    transmission.shiftUp()
    then:
    thrown ShiftNotPossibleException
  }

  def "Shifting up with maximum gears of #maxGear"() {
    given:
    def gt = new GearTransmission(maxGear)
    when:
    (1..maxGear).each {
      gt.shiftUp()
    }
    then:
    gt.currentGear == maxGear
    where:
    maxGear << [1, 3, 4, 5, 6, 7, 100]
  }

}
