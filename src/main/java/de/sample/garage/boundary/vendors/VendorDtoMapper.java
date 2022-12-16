package de.sample.garage.boundary.vendors;

import de.sample.garage.domain.vendors.Vendor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VendorDtoMapper {

    Vendor map(VendorDto source);
    VendorDto map(Vendor source);

}
