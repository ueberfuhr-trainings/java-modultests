package de.sample.garage.devtools;

import de.sample.garage.vendors.domain.Vendor;
import de.sample.garage.vendors.domain.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
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
