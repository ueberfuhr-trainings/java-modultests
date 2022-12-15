package de.sample.garage.persistence.vendors;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Getter
@Setter
@Entity(name = "Vendor")
@Table(name = "vendors")
public class VendorEntity {

    @Id
    private String shortName;
    private String name;
    private String city;

}
