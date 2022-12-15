package de.sample.garage.domain.vendors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VendorUpdatedEvent {

    @Getter
    private final Vendor vendor;

}
