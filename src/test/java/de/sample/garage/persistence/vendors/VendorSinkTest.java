package de.sample.garage.persistence.vendors;

import de.sample.garage.domain.vendors.Vendor;
import de.sample.garage.persistence.GarageJpaDataTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@GarageJpaDataTest
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
