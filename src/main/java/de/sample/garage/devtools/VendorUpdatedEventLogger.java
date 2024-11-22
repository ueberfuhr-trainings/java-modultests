package de.sample.garage.devtools;

import de.sample.garage.vendors.domain.VendorUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@Slf4j
public class VendorUpdatedEventLogger {

    @EventListener(VendorUpdatedEvent.class)
    public void logEvent(VendorUpdatedEvent event) {
        log.info("Updated vendor: " + event.vendor());
    }

}
