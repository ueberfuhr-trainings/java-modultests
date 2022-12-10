package de.sample.garage.boundary


import org.junit.jupiter.api.Tag
import org.springframework.test.context.ActiveProfiles

import java.lang.annotation.*

/**
 * Same as {@link GarageApiTest}, but creates Spock Mocks.
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@GarageApiTest
@ActiveProfiles(["test", "api-test", "spock"])
@Tag("spock-test")
@interface GarageApiSpockTest {
}
