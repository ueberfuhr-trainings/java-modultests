package de.sample.garage.vendors.boundary;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VendorDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String shortName;
    private String name;
    private String city;

}
