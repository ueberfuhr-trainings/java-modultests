package de.sample.garage.vendors.persistence;

import de.sample.garage.vendors.domain.Vendor;
import de.sample.garage.vendors.domain.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
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
    public Optional<Vendor> findByShortName(String shortName) {
        return repo.findById(shortName)
            .map(mapper::map);
    }

    @Override
    public void save(Vendor vendor) {
        repo.save(mapper.map(vendor));
    }


}
