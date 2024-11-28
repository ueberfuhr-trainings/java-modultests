package de.sample.garage.test.slices


import org.junit.jupiter.api.Tag
import org.springframework.test.context.ActiveProfiles

import java.lang.annotation.*

/**
 * Same as {@link VendorBoundaryTest}, but creates Spock Mocks.
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@VendorBoundaryTest
@ActiveProfiles(["test", "api-test", "spock"])
@Tag("spock-test")
@interface VendorBoundarySpockTest {
}
