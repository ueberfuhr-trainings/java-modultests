package de.sample.garage.domain.vendors.config;

import de.sample.garage.domain.vendors.Vendor;
import de.sample.garage.domain.vendors.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VendorSamplesInitializer {

    private final VendorService service;

    @EventListener(ContextRefreshedEvent.class)
    public void initializeData() {
        if (service.count() < 1) {
            service.save(
              Vendor.builder()
                .shortName("FORD")
                .name("Ford Motor Company")
                .city("Köln")
                .build()
            );
            service.save(
              Vendor.builder()
                .shortName("OPEL")
                .name("Opel Automobile GmbH")
                .city("Rüsselsheim")
                .build()
            );
        }
    }

}
