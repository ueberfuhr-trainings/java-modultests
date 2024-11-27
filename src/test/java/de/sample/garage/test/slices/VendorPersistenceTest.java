package de.sample.garage.test.slices;

import de.sample.garage.vendors.persistence.VendorRepository;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A test slice for persistence layer tests. Those tests share the same bean context,
 * that is configured to include
 * <ul>
 *     <li>Spring Data JPA components (repositories, entity manager...)</li>
 *     <li>components in the <tt>persistence</tt> package</li>
 *     <li><tt>test</tt> and <tt>jpa-test</tt> profiles</li>
 *     <li><tt>integration-test</tt> and <tt>jpa-test</tt> tags</li>
 * </ul>
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@DataJpaTest
@ComponentScan(basePackageClasses = VendorRepository.class)
@ActiveProfiles({"test", "jpa-test"})
@Tag("integration-test")
@Tag("jpa-test")
public @interface VendorPersistenceTest {

}
