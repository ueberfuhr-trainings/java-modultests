package de.sample.garage.test.config;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@TestConfiguration
@Profile("!spock")
public class MockProviderConfiguration {

    @Bean
    MockProvider mockito() {
        return Mockito::mock;
    }

}
