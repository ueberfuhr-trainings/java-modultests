package de.sample.garage.vendors.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Vendor {

    private String shortName;
    private String name;
    private String city;

}
