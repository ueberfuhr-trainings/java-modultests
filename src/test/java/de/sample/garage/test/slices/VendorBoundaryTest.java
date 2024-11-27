package de.sample.garage.test.slices;

import de.sample.garage.vendors.boundary.VendorController;
import de.sample.garage.vendors.domain.VendorService;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockReset;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.mockito.Mockito.mock;
import static org.springframework.test.context.bean.override.mockito.MockReset.withSettings;

/**
 * A test slice for API tests. Those tests share the same bean context,
 * that is configured to include
 * <ul>
 *     <li>Spring MVC components (controllers, controller advices...)</li>
 *     <li>MVC Mock</li>
 *     <li>components in the <tt>boundary</tt> package</li>
 *     <li>service mocks (we can get them using @{@link org.springframework.beans.factory.annotation.Autowired})</li>
 *     <li><tt>test</tt> and <tt>api-test</tt> profiles</li>
 *     <li><tt>integration-test</tt> and <tt>api-test</tt> tags</li>
 * </ul>
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@WebMvcTest
@ComponentScan(basePackageClasses = VendorController.class)
@ActiveProfiles({"test", "api-test"})
@Tag("integration-test")
@Tag("api-test")
@Import(VendorBoundaryTest.MockDomainConfiguration.class)
public @interface VendorBoundaryTest {

    @TestConfiguration
    class MockDomainConfiguration {

        @Bean
        VendorService vendorServiceMock() {
            return mock(
                VendorService.class,
                withSettings(MockReset.AFTER)
            );
        }

    }

}
