package de.sample.garage.domain;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class VendorService {

    private final Map<String, Vendor> vendors = new HashMap<>();

    public boolean containsData() {
        return !vendors.isEmpty();
    }

    public Collection<Vendor> findAll() {
        return vendors.values();
    }

    public void save(Vendor vendor) {
        this.vendors.put(vendor.getShortName(), vendor);
    }

}
