package de.sample.garage.vendors.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Configuration
@Slf4j
public class VendorServiceAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    VendorService.VendorSink inMemory() {
        log.info("Initializing in-memory vendor sink");
        return new InMemoryVendorSink();
    }

    static class InMemoryVendorSink implements VendorService.VendorSink {

        private final Map<String, Vendor> vendors = new HashMap<>();

        @Override
        public long count() {
            return vendors.size();
        }

        @Override
        public Stream<Vendor> findAll() {
            return vendors.values().stream();
        }

        @Override
        public Optional<Vendor> findByShortName(String shortName) {
            return Optional.ofNullable(vendors.get(shortName));
        }

        @Override
        public void save(Vendor vendor) {
            this.vendors.put(vendor.getShortName(), vendor);
        }

    }

}
