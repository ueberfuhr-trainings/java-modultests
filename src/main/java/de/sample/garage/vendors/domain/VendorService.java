package de.sample.garage.vendors.domain;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class VendorService {

    @Delegate(types = UnmodifiableVendorSink.class)
    private final VendorSink sink;
    private final ApplicationEventPublisher eventPublisher;

    public void save(Vendor vendor) {
        sink.save(vendor);
        eventPublisher.publishEvent(new VendorUpdatedEvent(vendor));
    }

    public interface VendorSink extends UnmodifiableVendorSink {

        void save(Vendor vendor);

    }

    @SuppressWarnings("unused")
    interface UnmodifiableVendorSink {

        long count();

        Stream<Vendor> findAll();

        Optional<Vendor> findByShortName(String shortName);

    }


}
