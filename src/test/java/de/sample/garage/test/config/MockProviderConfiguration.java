package de.sample.garage.test.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!spock")
public class MockProviderConfiguration {

    @Bean
    MockProvider mockito() {
        return Mockito::mock;
    }

}
