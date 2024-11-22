package de.sample.garage.vendors.persistence;

import de.sample.garage.vendors.domain.Vendor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VendorEntityMapper {

    Vendor map(VendorEntity source);

    VendorEntity map(Vendor source);

}
