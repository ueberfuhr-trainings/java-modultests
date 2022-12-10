package de.sample.garage.boundary.config;

import de.sample.garage.domain.VendorService;
import de.sample.garage.test.config.MockProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@TestConfiguration
@Profile("api-test")
@RequiredArgsConstructor
public class MockDomainConfiguration {

    private final MockProvider mockProvider;

    @Bean
    VendorService vendorServiceMock() {
        // be aware that this mock is strict, while @MockBean created mocks are lenient
        return mockProvider.mock(VendorService.class);
    }

}
