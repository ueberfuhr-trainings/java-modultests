package de.sample.garage.boundary.vendors;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VendorDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String shortName;
    private String name;
    private String city;

}
