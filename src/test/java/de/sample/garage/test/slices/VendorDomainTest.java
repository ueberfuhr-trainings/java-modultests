package de.sample.garage.test.slices;

import de.sample.garage.vendors.domain.VendorService;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockReset;
import org.springframework.test.context.event.RecordApplicationEvents;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.mockito.Mockito.mock;
import static org.springframework.test.context.bean.override.mockito.MockReset.withSettings;

/**
 * A test slice for domain tests. Those tests share the same bean context,
 * that is configured to include
 * <ul>
 *     <li>components in the <tt>domain</tt> package</li>
 *     <li>persistence mocks (we can get them using @{@link org.springframework.beans.factory.annotation.Autowired})</li>
 *     <li>recorded application events</li>
 *     <li><tt>test</tt> and <tt>domain-test</tt> profiles</li>
 *     <li><tt>integration-test</tt> and <tt>domain-test</tt> tags</li>
 * </ul>
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringBootTest(classes = VendorService.class)
@RecordApplicationEvents
@ActiveProfiles({"test", "domain-test"})
@Tag("integration-test")
@Tag("domain-test")
@Import(VendorDomainTest.MockDomainConfiguration.class)
public @interface VendorDomainTest {

    @TestConfiguration // if not set, the mock is not registered at the context
    class MockDomainConfiguration {

        // do not use mock() here directly, because @MockBean will register the mock for automatic reset
        @Bean
        VendorService.VendorSink vendorSinkMock() {
            return mock(
                VendorService.VendorSink.class,
                withSettings(MockReset.AFTER)
            );
        }

    }

}
