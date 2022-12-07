package de.sample.garage.boundary;

import de.sample.garage.domain.Vendor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VendorDtoMapper {

    Vendor map(VendorDto source);
    VendorDto map(Vendor source);

}
