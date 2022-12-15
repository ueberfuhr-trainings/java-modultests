package de.sample.garage.persistence.vendors;

import de.sample.garage.domain.vendors.Vendor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VendorEntityMapper {

    Vendor map(VendorEntity source);

    VendorEntity map(Vendor source);

}
