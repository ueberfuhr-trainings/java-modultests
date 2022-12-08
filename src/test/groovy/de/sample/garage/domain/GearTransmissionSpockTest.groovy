package de.sample.garage.domain

import spock.lang.Specification

class GearTransmissionSpockTest extends Specification {

  private static final int MAXGEAR = 6

  private final GearTransmission transmission = new GearTransmission(MAXGEAR)

  def "constructor should throw IllegalArgumentException"() {
    verifyAll {

    }
  }



}
