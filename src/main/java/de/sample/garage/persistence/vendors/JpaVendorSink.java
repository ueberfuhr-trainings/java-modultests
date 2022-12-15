package de.sample.garage.persistence.vendors;

import de.sample.garage.domain.vendors.Vendor;
import de.sample.garage.domain.vendors.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JpaVendorSink implements VendorService.VendorSink {

    private final VendorRepository repo;
    private final VendorEntityMapper mapper;

    @Override
    public long count() {
        return repo.count();
    }

    @Override
    public Stream<Vendor> findAll() {
        return repo.findAll()
          .stream()
          .map(mapper::map);
    }

    @Override
    public void save(Vendor vendor) {
        repo.save(mapper.map(vendor));
    }


}
