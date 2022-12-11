package de.sample.garage.boundary.config;

import de.sample.garage.domain.VendorService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;

@TestConfiguration
@Profile("api-test")
public class MockDomainConfiguration {

    @Bean
    VendorService vendorServiceMock() {
        // be aware that this mock is strict, while @MockBean created mocks are lenient
        return mock(VendorService.class);
    }

}
