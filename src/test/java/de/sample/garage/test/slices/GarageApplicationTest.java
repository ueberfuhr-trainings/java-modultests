package de.sample.garage.test.slices;

import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A test slice for full-platform tests. Those tests share the same bean context,
 * that is configured to include all components. Only the database is replaced by a test database.
 * <ul>
 *     <li>all Spring components</li>
 *     <li>MVC Mock</li>
 *     <li>Test Database</li>
 *     <li><tt>test</tt> and <tt>full-integration-test</tt> profiles</li>
 *     <li><tt>integration-test</tt> and <tt>full-integration-test</tt> tags</li>
 * </ul>
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringBootTest
@AutoConfigureMockMvc
// otherwise, it uses the file based
// -> this would crash, when the app or another test is already running in parallel
@AutoConfigureTestDatabase
@ActiveProfiles({"test", "full-integration-test"})
@Tag("integration-test")
@Tag("full-integration-test")
public @interface GarageApplicationTest {

}
