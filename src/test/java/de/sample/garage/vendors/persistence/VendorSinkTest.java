package de.sample.garage.vendors.persistence;

import de.sample.garage.test.slices.VendorPersistenceTest;
import de.sample.garage.vendors.domain.Vendor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

@VendorPersistenceTest
public class VendorSinkTest {

    @Autowired
    JpaVendorSink sink;

    @Test
    void shouldRestoreVendor() {
        // arrange
        var vendor = Vendor.builder()
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
