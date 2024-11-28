package de.sample.garage.test.config

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Profile
import spock.mock.DetachedMockFactory

@TestConfiguration
@Profile("spock")
class SpockMockProviderConfiguration {

    private final mockFactory = new DetachedMockFactory()

    @Bean
    MockProvider spockMockProvider() {
        return mockFactory.&Mock
    }

}
