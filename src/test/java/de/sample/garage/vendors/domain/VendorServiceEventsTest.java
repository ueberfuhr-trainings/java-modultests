package de.sample.garage.vendors.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.event.ApplicationEvents;
import org.springframework.test.context.event.RecordApplicationEvents;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
@AutoConfigureTestDatabase
@RecordApplicationEvents
public class VendorServiceEventsTest {

    @Autowired
    VendorService service;
    @MockitoBean
    VendorService.VendorSink sink;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    ApplicationEvents events;

    @Test
    void shouldRecordEventOnSaveVendor() {
        Vendor vendor = Vendor.builder()
                .shortName("test")
                .name("test-name")
                .city("test-city")
                .build();
        service.save(vendor);
        assertThat(events.stream(VendorUpdatedEvent.class))
                .hasSize(1)
                .first()
                .extracting(VendorUpdatedEvent::vendor)
                .isEqualTo(vendor);
    }

    @Test
    void shouldNotRecordEventOnSaveVendorError() {
        Vendor vendor = Vendor.builder()
                .shortName("test")
                .name("test-name")
                .city("test-city")
                .build();
        DataAccessException ex = new DataAccessException("test exception") {
        };
        doThrow(ex).when(sink).save(vendor);
        assertThatThrownBy(() -> service.save(vendor))
                .isSameAs(ex);
        assertThat(events.stream(VendorUpdatedEvent.class))
                .isEmpty();
    }

}
