package de.sample.garage.vendors.boundary;

import de.sample.garage.vendors.domain.Vendor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VendorDtoMapper {

    Vendor map(VendorDto source);

    VendorDto map(Vendor source);

}
