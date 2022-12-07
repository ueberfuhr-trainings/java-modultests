package de.sample.garage.domain.config;

import de.sample.garage.domain.Vendor;
import de.sample.garage.domain.VendorService;
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
        if (!service.containsData()) {
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
                .name("Opel AUtomobile GmbH")
                .city("Rüsselsheim")
                .build()
            );
        }
    }

}
