package de.sample.garage.persistence.vendors;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<VendorEntity, String> {
}
