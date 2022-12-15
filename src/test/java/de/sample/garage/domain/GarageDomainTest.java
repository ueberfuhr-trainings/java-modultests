package de.sample.garage.domain;

import de.sample.garage.domain.vendors.VendorService;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.RecordApplicationEvents;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
@ActiveProfiles({ "test", "domain-test" })
@Tag("integration-test")
@Tag("domain-test")
@Import(GarageDomainTest.MockDomainConfiguration.class)
public @interface GarageDomainTest {

    @TestConfiguration // if not set, the mock is not registered at the context
    class MockDomainConfiguration {

        // do not use mock() here directly, because @MockBean will register the mock for automatic reset
        @MockBean // Mock is injected but not registered in the context
//        @Getter(onMethod_ = @Bean(name = "vendorSinkMock")) // register in the context
        VendorService.VendorSink vendorSink;

    }

}
