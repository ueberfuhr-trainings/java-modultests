package de.sample.garage.boundary;

import de.sample.garage.domain.VendorService;
import de.sample.garage.test.config.MockProvider;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A test slice for API tests. Those tests share the same bean context,
 * that is configured to include
 * <ul>
 *     <li>Spring MVC components (controllers, controller advices...)</li>
 *     <li>MVC Mock</li>
 *     <li>components in the <tt>boundary</tt> package</li>
 *     <li>service mocks using Mockito (we can get them using @{@link org.springframework.beans.factory.annotation.Autowired})</li>
 *     <li><tt>test</tt> and <tt>api-test</tt> profiles</li>
 *     <li><tt>integration-test</tt> and <tt>api-test</tt> tags</li>
 * </ul>
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@WebMvcTest
@ComponentScan(basePackageClasses = { GarageApiTest.class, MockProvider.class })
@ActiveProfiles({ "test", "api-test" })
@Tag("integration-test")
@Tag("api-test")
@Import(GarageApiTest.MockDomainConfiguration.class)
public @interface GarageApiTest {

    // we don't need this because we use @Import
    // @TestConfiguration
    // @Profile("api-test")
    @RequiredArgsConstructor
    class MockDomainConfiguration {

        private final MockProvider mockProvider;

        @Bean
        VendorService vendorServiceMock() {
            // be aware that this mock is strict, while @MockBean created mocks are lenient
            return mockProvider.mock(VendorService.class);
        }

    }

}
