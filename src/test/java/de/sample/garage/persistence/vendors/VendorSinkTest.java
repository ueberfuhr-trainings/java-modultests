package de.sample.garage.persistence.vendors;

import de.sample.garage.domain.vendors.Vendor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
// otherwise, it uses the file based
// -> this would crash, when the app or another test is already running in parallel
@AutoConfigureTestDatabase
public class VendorSinkTest {

    @Autowired
    JpaVendorSink sink;

    @Test
    void shouldRestoreVendor() {
        // arrange
        Vendor vendor = Vendor.builder()
          .shortName("test")
          .name("test-name")
          .city("test-city")
          .build();
        sink.save(vendor);
        // act and assert
        assertThat(sink.findByShortName("test"))
          .isNotEmpty()
          .contains(vendor);
    }

}
