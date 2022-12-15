package de.sample.garage.domain;

import de.sample.garage.domain.vendors.VendorService;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.RecordApplicationEvents;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.mockito.Mockito.mock;

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
@Import(GarageDomainTest.MockDomainConfiguration.class)
public @interface GarageDomainTest {


    // we don't need this because we use @Import
    // @TestConfiguration
    // @Profile("api-test")
    class MockDomainConfiguration {

        @Bean
        VendorService.VendorSink vendorSinkMock() {
            // be aware that this mock is strict, while @MockBean created mocks are lenient
            return mock(VendorService.VendorSink.class);
        }

    }

}
