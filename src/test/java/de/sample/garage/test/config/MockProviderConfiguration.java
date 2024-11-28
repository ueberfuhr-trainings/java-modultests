package de.sample.garage.test.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;
import static org.springframework.test.context.bean.override.mockito.MockReset.BEFORE;
import static org.springframework.test.context.bean.override.mockito.MockReset.withSettings;

@TestConfiguration
@Profile("!spock")
public class MockProviderConfiguration {

    private <T> T createMock(Class<T> mockClass) {
        return mock(
            mockClass,
            withSettings(BEFORE)
        );
    }

    @Bean
    MockProvider mockitoMockProvider() {
        return this::createMock;
    }

}
