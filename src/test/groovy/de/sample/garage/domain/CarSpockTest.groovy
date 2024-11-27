package de.sample.garage.domain

import spock.lang.Specification

class CarSpockTest extends Specification {

  final def engine = Mock(Engine)
  final def transmission = Mock(GearTransmission)
  final def tank = Mock(GasTank)
  final def clutch = Mock(Clutch)

  final def car = [clutch, engine, tank, transmission] as Car

  //FillUpTests

  def "should not fill up with started engine"() {
    when:
    car.fillUp(5d)
    then:
    thrown IllegalStateException
    1 * engine.engineStarted >> true
    0 * tank.fillUp(_)
  }

  def "should not fill up with amount not possible"() {
    when:
    car.fillUp(5d)
    then:
    1 * engine.engineStarted >> false
    1 * tank.isAmountPossible(5d) >> false
    0 * tank.fillUp(_)
  }

  def "should fill up"() {
    when:
    car.fillUp(5d)
    then:
    1 * engine.engineStarted >> false
    1 * tank.isAmountPossible(5d) >> true
    1 * tank.fillUp(5d)
  }


}
