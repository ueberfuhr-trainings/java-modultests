package de.sample.garage.test.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import spock.mock.DetachedMockFactory

@Configuration
@Profile("spock")
class SpockMockProviderConfiguration {

  private final mockFactory = new DetachedMockFactory()

  @Bean
  MockProvider spockMockProvider() {
    return mockFactory.&Mock
  }

}
